package com.example.testapplication.data.network

import com.squareup.moshi.Json
import retrofit2.Call
import retrofit2.http.GET

interface UsersService {
    @GET("users/")
    fun getUsersData(): Call<List<UserRestModel>>
}

//@JsonClass(generateAdapter = true)
data class UsersRestModel(
    val users: List<UserRestModel>
)

//@JsonClass(generateAdapter = true)
data class UserRestModel(
    val id: Int,
    @field:Json(name = "first_name") val firstName: String,
    @field:Json(name = "last_name") val lastName: String,
    val email: String,
    val gender: String,
    val avatar: String,
)