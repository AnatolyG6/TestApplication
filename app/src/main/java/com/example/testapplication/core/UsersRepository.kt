package com.example.testapplication.core

import com.example.testapplication.core.entities.User

interface UsersRepository {
    fun getAllUsers(): List<User>?
}