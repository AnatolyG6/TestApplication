package com.example.testapplication.core.usecase

import com.example.testapplication.core.ExampleRepository
import javax.inject.Inject

class GetExampleUseCase @Inject constructor(private val exampleRepository: ExampleRepository) {
    fun execute(listener: ExampleListener) {
        listener.getData(exampleRepository.getSomeData())
    }
}

interface ExampleListener {
    fun getData(someData: Unit)
}