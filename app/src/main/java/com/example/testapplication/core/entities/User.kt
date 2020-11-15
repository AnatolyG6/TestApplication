package com.example.testapplication.core.entities

data class User(
    val id: Int,
    val firstName: String,
    val lastName: String,
    val email: String,
    val gender: String,
    val avatarUrl: String
)