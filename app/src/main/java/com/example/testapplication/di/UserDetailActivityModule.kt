package com.example.testapplication.di

import com.example.testapplication.app.userdetail.UserDetailActivity
import dagger.Module
import dagger.Provides

@Module
class UserDetailActivityModule (private val context: UserDetailActivity) {
    @Provides
    fun context(): UserDetailActivity {
        return context
    }
}