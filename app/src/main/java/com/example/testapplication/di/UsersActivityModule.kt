package com.example.testapplication.di

import android.content.Context
import dagger.Module
import dagger.Provides

@Module
class UsersActivityModule(private val context: Context) {
    @Provides
    fun context(): Context {
        return context
    }
}