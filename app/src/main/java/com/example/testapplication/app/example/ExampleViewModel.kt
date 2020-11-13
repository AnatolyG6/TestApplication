package com.example.testapplication.app.example

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.testapplication.core.usecase.ExampleListener
import com.example.testapplication.core.usecase.GetExampleUseCase
import javax.inject.Inject

class ExampleViewModel @Inject constructor(private val exampleUseCase: GetExampleUseCase) {
    private val _exampleState = MutableLiveData<Boolean>()
    val exampleState: LiveData<Boolean> = _exampleState

    fun action(action: Action) {
        when (action) {
            Action.ExampleAction -> exampleUseCase.execute(
                object: ExampleListener {
                    override fun getData(someData: Unit) {
                        TODO("do some action")
                        exampleState.value = true
                    }
                }
            )
        }
    }
}