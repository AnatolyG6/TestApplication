package com.example.testapplication.di

import android.content.Context
import android.net.ConnectivityManager
import com.example.testapplication.BuildConfig
import com.example.testapplication.data.network.ExampleService
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
open class ServiceModule(private val cacheDirectory: File, private val context: Context) {
    private val connectivityManager: ConnectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    @Provides
    @Singleton
    internal fun provideService(retrofit: Retrofit): ExampleService {
        return retrofit.create(ExampleService::class.java)
    }

    @Provides
    @Singleton
    internal fun provideRetrofit(
        client: OkHttpClient,
        converterFactory: Converter.Factory
    ): Retrofit {
        val baseUrl = sanitizeUrl(BASE_URL)
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(client)
            .addConverterFactory(converterFactory)
            .build()
    }

    private fun sanitizeUrl(baseUrl: String): String {
        return if (baseUrl.endsWith("/")) {
            baseUrl
        } else baseUrl + "/"
    }

    @Provides
    @Singleton
    internal fun provideLoggingInterceptor(logger: HttpLoggingInterceptor.Logger): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor(logger)
        if (BuildConfig.DEBUG && LOG_NETWORK) {
            interceptor.level = networkLogLevel
        }
        return interceptor
    }

    private val networkLogLevel: HttpLoggingInterceptor.Level
        get() = HttpLoggingInterceptor.Level.valueOf(LOG_NETWORK_LEVEL)

    @Provides
    @Singleton
    internal fun provideConnectivityManager(): ConnectivityManager {
        return connectivityManager
    }

    @Provides
    @Singleton
    internal fun provideClient(
        loggingInterceptor: HttpLoggingInterceptor,
        cache: Cache?
    ): OkHttpClient {
        val builder = OkHttpClient.Builder()
            .cache(cache)
            .connectTimeout(TIMEOUT.toLong(), TimeUnit.SECONDS)
            .readTimeout(TIMEOUT.toLong(), TimeUnit.SECONDS)
            .writeTimeout(TIMEOUT.toLong(), TimeUnit.SECONDS)
            .addInterceptor(loggingInterceptor)
        return builder.build()
    }


    @Provides
    @Singleton
    internal fun provideJsonConverterFactory(moshi: Moshi): Converter.Factory =
        MoshiConverterFactory.create(moshi)

    @Provides
    internal fun provideMoshi(): Moshi = Moshi.Builder().build()


    @Provides
    @Singleton
    internal fun provideCache(@Named("cacheDir") cacheDirectory: File): Cache? =
        if (BuildConfig.DEBUG && !ENABLE_CACHE) null
        else Cache(cacheDirectory, CACHE_SIZE)

    @Provides
    @Named("cacheDir")
    @Singleton
    internal fun provideCacheDirectory(): File = cacheDirectory

    companion object {
        private val BASE_URL = "https//:www.some-site.com"
        private val LOG_NETWORK = false
        private val TIMEOUT = 10
        private val LOG_NETWORK_LEVEL = "NONE"
        private val CACHE_SIZE = 10L
        private val ENABLE_CACHE = true
    }
}
