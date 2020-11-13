package com.example.testapplication.app.usersview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.testapplication.app.usersview.adapter.UserUiMapper
import com.example.testapplication.app.usersview.adapter.UserUiModel
import com.example.testapplication.core.entities.User
import com.example.testapplication.core.usecase.GetAllUsersUseCase
import com.example.testapplication.core.usecase.UsersListener
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class UsersViewModel @Inject constructor(
    private val useCase: GetAllUsersUseCase,
    private val mapper: UserUiMapper
) {
    private val _content = MutableLiveData<List<UserUiModel>>()
    val content: LiveData<List<UserUiModel>> = _content

    fun action(action: Action) {
        when(action) {
            Action.InitContent -> getDataContent()
        }
    }

    private fun getDataContent() {
        GlobalScope.launch {
            useCase.execute(
                object : UsersListener {
                    override fun getUsers(users: List<User>?) {
                        _content.postValue( mapper.mapUsers(users)
                        )
                    }
                }
            )
        }
    }
}