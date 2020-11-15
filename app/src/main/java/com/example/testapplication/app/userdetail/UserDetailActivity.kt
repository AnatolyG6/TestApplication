package com.example.testapplication.app.userdetail

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.example.testapplication.di.DaggerApplicationComponent
import com.example.testapplication.di.UserDetailActivityModule

class UserDetailActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DaggerApplicationComponent.builder()
            .userDetailActivityModule(UserDetailActivityModule(this))
            .build().inject(this)
    }

    companion object {
        fun createIntent(
            context: Context,
            userName: String,
            userGender: String,
            userEmail: String,
            userAvatar: String
        ): Intent {

            return Intent(context, UserDetailActivity::class.java).apply {
                putExtra(USER_NAME_KEY, userName)
                putExtra(USER_GENDER_KEY, userGender)
                putExtra(USER_EMAIL_KEY, userEmail)
                putExtra(USER_AVATAR_KEY, userAvatar)
            }
        }

        private val USER_NAME_KEY = "user_name_key"
        private val USER_GENDER_KEY = "user_gender_key"
        private val USER_EMAIL_KEY = "user_email_key"
        private val USER_AVATAR_KEY = "user_avatar_key"
    }
}