package com.example.testapplication.di

import android.content.Context
import com.example.testapplication.app.usersview.UsersActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [UsersActivityModule::class, ExampleRepositoryModule::class, ServiceModule::class])
interface ApplicationComponent {
    fun context(): Context
    fun inject(usersActivity: UsersActivity)
}