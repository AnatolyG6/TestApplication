package com.example.testapplication.app.usersview

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.testapplication.app.usersview.adapter.UserAdapter
import com.example.testapplication.app.usersview.adapter.UserUiModel
import com.example.testapplication.databinding.ActivityUsersBinding
import com.example.testapplication.di.DaggerApplicationComponent
import com.example.testapplication.di.ServiceModule
import com.example.testapplication.di.UsersActivityModule
import javax.inject.Inject

class UsersActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModel: UsersViewModel
    private lateinit var binding: ActivityUsersBinding
    private val adapter = UserAdapter(getOnClickListener())

    private fun getOnClickListener(): UserAdapter.OnItemClickListener {
        return object: UserAdapter.OnItemClickListener {
            override fun onClick(userUiModel: UserUiModel) {
                TODO("Not yet implemented")
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DaggerApplicationComponent.builder()
            .usersActivityModule(UsersActivityModule(this))
            .serviceModule(ServiceModule(this))
            .build().inject(this)
        binding = ActivityUsersBinding.inflate(layoutInflater)
        setContentView(binding.getRoot())
        setupContent()
        setupObserver()
        setupAdapter()
    }

    private fun setupAdapter() {
        binding.usersRecyclerView.adapter = adapter
        binding.usersRecyclerView.layoutManager = LinearLayoutManager(this)
    }

    private fun setupObserver() {
        viewModel.content.observe(
            this,
            Observer {
                adapter.submitList(it)
            }
        )
    }


    private fun setupContent() {
        viewModel.action(Action.InitContent)
    }

    companion object{
        fun createIntent(context: Context): Intent {
            return Intent(context, UsersActivity::class.java)
        }
    }
}