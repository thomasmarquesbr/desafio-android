package com.picpay.desafio.android.features.users

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.picpay.desafio.android.R
import com.picpay.desafio.android.features.users.model.UserUIModel
import com.thomas.components.databinding.ListItemUserBinding

class UserListAdapter : ListAdapter<UserUIModel, RecyclerView.ViewHolder>(Companion) {

    companion object : DiffUtil.ItemCallback<UserUIModel>() {
        override fun areItemsTheSame(oldItem: UserUIModel, newItem: UserUIModel): Boolean =
            oldItem === newItem

        override fun areContentsTheSame(oldItem: UserUIModel, newItem: UserUIModel): Boolean =
            oldItem.id == newItem.id
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ItemViewHolder.from(parent)

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ItemViewHolder).bind(currentList[position])
    }

    class ItemViewHolder private constructor(
        private val binding: ListItemUserBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: UserUIModel) {
            binding.run {
                imagePlaceholder =
                    ContextCompat.getDrawable(root.context, R.drawable.ic_round_account_circle)
                imageUrl = item.img
                title = item.username
                description = item.name
            }
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ItemViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ListItemUserBinding.inflate(layoutInflater, parent, false)
                return ItemViewHolder(binding)
            }
        }

    }

}
