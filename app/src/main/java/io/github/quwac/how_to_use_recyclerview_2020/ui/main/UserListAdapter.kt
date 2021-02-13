package io.github.quwac.how_to_use_recyclerview_2020.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import io.github.quwac.how_to_use_recyclerview_2020.databinding.UserViewSwitchableBinding
import io.github.quwac.how_to_use_recyclerview_2020.databinding.UserViewUnswitchableBinding

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

    abstract class UserViewHolder(binding: ViewDataBinding) :
        RecyclerView.ViewHolder(binding.root) {
        abstract fun bind(item: User, viewLifecycleOwner: LifecycleOwner, viewModel: MainViewModel)
    }

    class UserViewSwitchableHolder(private val binding: UserViewSwitchableBinding) :
        UserViewHolder(binding) {
        override fun bind(
            item: User,
            viewLifecycleOwner: LifecycleOwner,
            viewModel: MainViewModel
        ) {
            binding.run {
                lifecycleOwner = viewLifecycleOwner
                user = item
                this.viewModel = viewModel

                executePendingBindings()
            }
        }
    }

    class UserViewUnswitchableHolder(private val binding: UserViewUnswitchableBinding) :
        UserViewHolder(binding) {
        override fun bind(
            item: User,
            viewLifecycleOwner: LifecycleOwner,
            viewModel: MainViewModel
        ) {
            binding.run {
                user = item

                executePendingBindings()
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return getItem(position).userType.ordinal
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)

        @Suppress("MoveVariableDeclarationIntoWhen")
        val type = UserType.values()[viewType]
        return when (type) {
            UserType.USER_SWITCHABLE -> UserViewSwitchableHolder(
                UserViewSwitchableBinding.inflate(
                    layoutInflater,
                    parent,
                    false
                )
            )
            UserType.USER_UNSWITCHABLE -> UserViewUnswitchableHolder(
                UserViewUnswitchableBinding.inflate(
                    layoutInflater,
                    parent,
                    false
                )
            )
        }
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(getItem(position), viewLifecycleOwner, viewModel)
    }

}
