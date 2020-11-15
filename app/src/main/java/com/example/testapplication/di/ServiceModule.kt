package com.example.testapplication.di

import android.content.Context
import android.net.ConnectivityManager
import com.example.testapplication.data.network.ExampleService
import com.example.testapplication.data.network.UsersService
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
open class ServiceModule(private val context: Context) {
    private val connectivityManager: ConnectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    @Provides
    @Singleton
    internal fun provideService(retrofit: Retrofit): ExampleService {
        return retrofit.create(ExampleService::class.java)
    }

    @Provides
    @Singleton
    internal fun provideUsersService(retrofit: Retrofit): UsersService {
        return retrofit.create(UsersService::class.java)
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
    internal fun provideConnectivityManager(): ConnectivityManager {
        return connectivityManager
    }

    @Provides
    @Singleton
    internal fun provideClient(): OkHttpClient {
        val builder = OkHttpClient.Builder()
            .connectTimeout(TIMEOUT.toLong(), TimeUnit.SECONDS)
            .readTimeout(TIMEOUT.toLong(), TimeUnit.SECONDS)
            .writeTimeout(TIMEOUT.toLong(), TimeUnit.SECONDS)
        return builder.build()
    }


    @Provides
    @Singleton
    internal fun provideJsonConverterFactory(moshi: Moshi): Converter.Factory =
        MoshiConverterFactory.create(moshi)

    @Provides
    internal fun provideMoshi(): Moshi = Moshi.Builder().build()


    companion object {
        private val BASE_URL = "http://server.lunabee.studio:11111/techtest/"
        private val TIMEOUT = 10
    }
}
