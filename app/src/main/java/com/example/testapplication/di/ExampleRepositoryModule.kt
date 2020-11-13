package com.example.testapplication.di

import com.example.testapplication.core.ExampleRepository
import com.example.testapplication.core.UsersRepository
import com.example.testapplication.data.ExampleRepositoryImpl
import com.example.testapplication.data.UsersRepositoryImpl
import dagger.Binds
import dagger.Module

@Module
internal abstract class ExampleRepositoryModule {

    @Binds
    abstract fun bindExampleRepository(
        exampleRepository: ExampleRepositoryImpl
    ): ExampleRepository

    @Binds
    abstract fun bindUsersRepository(
        userRepository: UsersRepositoryImpl
    ): UsersRepository
}