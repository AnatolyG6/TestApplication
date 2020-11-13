package com.example.testapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.testapplication.di.DaggerApplicationComponent
import com.example.testapplication.di.MainActivityModule

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        DaggerApplicationComponent.builder()
            .mainActivityModule(MainActivityModule(this))
            .build().inject(this)
    }
}