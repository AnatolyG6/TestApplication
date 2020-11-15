package com.example.testapplication.core.usecase

import com.example.testapplication.core.UsersRepository
import com.example.testapplication.core.entities.User
import javax.inject.Inject

class GetAllUsersUseCase @Inject constructor(private val repository: UsersRepository) {

    fun execute(listener: UsersListener) {
        listener.getUsers(repository.getAllUsers())
    }
}

interface UsersListener {
    fun getUsers(users: List<User>?)
}