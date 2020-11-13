package com.example.testapplication.di

import com.example.testapplication.core.ExampleRepository
import com.example.testapplication.data.ExampleRepositoryImpl
import dagger.Binds
import dagger.Module

@Module
internal abstract class ExampleRepositoryModule {

    @Binds
    abstract fun bindExampleRepository(
        exampleRepository: ExampleRepositoryImpl
    ): ExampleRepository
}