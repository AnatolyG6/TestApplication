package com.example.testapplication.di

import com.example.testapplication.app.usersview.UsersActivity
import dagger.Module
import dagger.Provides

@Module
class UsersActivityModule(private val context: UsersActivity) {
    @Provides
    fun context(): UsersActivity {
        return context
    }
}