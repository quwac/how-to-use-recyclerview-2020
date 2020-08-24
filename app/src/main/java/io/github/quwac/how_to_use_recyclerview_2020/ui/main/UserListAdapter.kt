package io.github.quwac.how_to_use_recyclerview_2020.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import io.github.quwac.how_to_use_recyclerview_2020.databinding.UserViewBinding

private object DiffCallback : DiffUtil.ItemCallback<User>() {
    override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem == newItem
    }

}

class UserListAdapter(
    private val viewLifecycleOwner: LifecycleOwner,
    private val viewModel: MainViewModel
) : ListAdapter<User, UserListAdapter.UserViewHolder>(DiffCallback) {

    class UserViewHolder(private val binding: UserViewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: User, viewLifecycleOwner: LifecycleOwner, viewModel: MainViewModel) {
            binding.run {
                lifecycleOwner = viewLifecycleOwner
                user = item
                this.viewModel = viewModel

                executePendingBindings()
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return UserViewHolder(UserViewBinding.inflate(layoutInflater, parent, false))
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(getItem(position), viewLifecycleOwner, viewModel)
    }

}
