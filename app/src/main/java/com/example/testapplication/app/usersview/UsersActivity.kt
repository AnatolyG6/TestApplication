package com.example.testapplication.app.usersview

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.testapplication.R
import com.example.testapplication.app.userdetail.UserDetailActivity
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
        return object : UserAdapter.OnItemClickListener {
            override fun onClick(userUiModel: UserUiModel) {
                startActivity(
                    UserDetailActivity.createIntent(
                        this@UsersActivity,
                        userUiModel.name,
                        userUiModel.gender,
                        userUiModel.email,
                        userUiModel.avatarUrl
                    )
                )
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
        setupSearch()
        setupContent()
        setupObserver()
        setupAdapter()
    }

    private fun setupSearch() {
        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchView = binding.usersToolbar.menu.findItem(R.id.search_option)
            .actionView as SearchView
        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))
        searchView.setOnCloseListener(
            object : SearchView.OnCloseListener {
                override fun onClose(): Boolean {
                    viewModel.action(Action.InitContent)
                    return true
                }
            }
        )
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


    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        handleSearch(intent)
    }

    private fun handleSearch(intent: Intent) {
        if (intent.action == Intent.ACTION_SEARCH) {
            val query = intent.getStringExtra(SearchManager.QUERY)
            viewModel.action(Action.SearchUser(query))
        }
    }

    companion object {
        fun createIntent(context: Context): Intent {
            return Intent(context, UsersActivity::class.java)
        }
    }
}