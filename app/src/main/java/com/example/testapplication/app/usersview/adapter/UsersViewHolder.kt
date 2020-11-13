package com.example.testapplication.app.usersview.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.testapplication.databinding.ItemUserBinding

class UsersViewHolder(val binding: ItemUserBinding) : RecyclerView.ViewHolder(binding.root) {
    companion object {
        fun create(parent: ViewGroup): UsersViewHolder {
            val view = ItemUserBinding.inflate(LayoutInflater.from(parent.context))

            return UsersViewHolder(view)
        }
    }

    fun bind(userUiModel: UserUiModel, listener: UserAdapter.OnItemClickListener) {
        binding.apply {
            userName.text = userUiModel.name
            userGenre.text = userUiModel.gender
            userEmail.text = userUiModel.email
            userAvatar.load(userUiModel.avatarUrl)
        }
    }

}