package com.example.testapplication.di

import com.example.testapplication.app.userdetail.UserDetailActivity
import com.example.testapplication.app.usersview.UsersActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules =
    [
        UsersActivityModule::class,
        ExampleRepositoryModule::class,
        ServiceModule::class,
        UserDetailActivityModule::class
    ]
)
interface ApplicationComponent {
    fun inject(usersActivity: UsersActivity)
    fun inject(userDetailActivity: UserDetailActivity)
}