package com.example.testapplication.app.usersview.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter

class UserAdapter(
    private val listener: OnItemClickListener
) : ListAdapter<UserUiModel, UsersViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersViewHolder {
        return UsersViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: UsersViewHolder, position: Int) {
        holder.bind(getItem(position), listener)
    }

    interface OnItemClickListener {
        fun onClick(userUiModel: UserUiModel)
    }
}

private class DiffCallback : DiffUtil.ItemCallback<UserUiModel>() {
    override fun areItemsTheSame(oldItem: UserUiModel, newItem: UserUiModel): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: UserUiModel, newItem: UserUiModel): Boolean {
        return oldItem == newItem
    }

}