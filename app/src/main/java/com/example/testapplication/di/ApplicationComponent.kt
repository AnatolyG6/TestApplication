package com.example.testapplication.di

import android.content.Context
import com.example.testapplication.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [MainActivityModule::class, ExampleRepositoryModule::class, ServiceModule::class])
interface ApplicationComponent {
    fun context(): Context
    fun inject(main: MainActivity)
}