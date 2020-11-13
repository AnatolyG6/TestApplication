package com.example.testapplication.data

import com.example.testapplication.core.ExampleRepository
import javax.inject.Inject

class ExampleRepositoryImpl @Inject constructor() : ExampleRepository {
    override fun getSomeData() {
        TODO("Just for example")
    }

}