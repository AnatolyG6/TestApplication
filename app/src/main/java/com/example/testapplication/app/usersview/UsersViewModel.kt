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
import java.util.*
import javax.inject.Inject

class UsersViewModel @Inject constructor(
    private val useCase: GetAllUsersUseCase,
    private val mapper: UserUiMapper
) {
    private val _content = MutableLiveData<List<UserUiModel>>()
    val content: LiveData<List<UserUiModel>> = _content

    private var cachedUserList: List<UserUiModel>? = null

    fun action(action: Action) {
        when (action) {
            is Action.InitContent -> getDataContent()
            is Action.SearchUser -> filterUserList(action.query)
        }
    }

    private fun getDataContent() {
        GlobalScope.launch {
            useCase.execute(
                object : UsersListener {
                    override fun getUsers(users: List<User>?) {
                        cachedUserList = mapper.mapUsers(users)
                        _content.postValue(cachedUserList)
                    }
                }
            )
        }
    }

    private fun filterUserList(query: String?) {
        if (query != null) {
            val tmpCachedUserList = cachedUserList?.filter {
                it.name.toLowerCase(Locale.ROOT).contains(query.toLowerCase(Locale.ROOT))
            }
            _content.value = tmpCachedUserList
        }
    }
}
