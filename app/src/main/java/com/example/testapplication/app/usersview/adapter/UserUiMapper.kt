package com.example.testapplication.app.usersview.adapter

import com.example.testapplication.core.entities.User
import javax.inject.Inject

class UserUiMapper @Inject constructor() {
    fun mapUsers(users: List<User>?): List<UserUiModel>? {
        return users?.map {
            UserUiModel(
                it.id,
                it.firstName + " " + it.lastName,
                it.email,
                it.gender,
                it.avatarUrl
            )
        }
    }
}