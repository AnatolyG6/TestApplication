package com.example.testapplication.app.usersview

sealed class Action {
    data class SearchUser(val query: String?) : Action()
    object InitContent : Action()
}