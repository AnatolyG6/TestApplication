package com.example.testapplication.di

import android.content.Context
import dagger.Module
import dagger.Provides

@Module
class MainActivityModule(private val context: Context) {
    @Provides
    fun context(): Context {
        return context
    }
}