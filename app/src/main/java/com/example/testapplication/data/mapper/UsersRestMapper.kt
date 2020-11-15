package com.example.testapplication.data.mapper

import com.example.testapplication.core.entities.User
import com.example.testapplication.data.network.UserRestModel
import com.example.testapplication.data.network.UsersRestModel
import javax.inject.Inject

class UsersRestMapper @Inject constructor() {
    fun mapUsers(restUsers: List<UserRestModel>): List<User> {
        return restUsers.map{
            User(
                it.id,
                it.firstName,
                it.lastName,
                it.email,
                it.gender,
                it.avatar
            )
        }
    }
}