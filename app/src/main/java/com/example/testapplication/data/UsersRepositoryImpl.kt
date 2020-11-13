package com.example.testapplication.data

import com.example.testapplication.core.UsersRepository
import com.example.testapplication.core.entities.User
import com.example.testapplication.data.mapper.UsersRestMapper
import com.example.testapplication.data.network.UsersService
import javax.inject.Inject

class UsersRepositoryImpl @Inject constructor(
    private val service: UsersService,
    private val mapper: UsersRestMapper
) : UsersRepository {

    override fun getAllUsers(): List<User>? {
        val test = service.getUsersData().request().url().toString()
        val response = service.getUsersData().execute()
        return if (response?.body() != null) {
            mapper.mapUsers(response.body()!!)
        } else null
    }
}