package io.github.quwac.how_to_use_recyclerview_2020.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import io.github.quwac.how_to_use_recyclerview_2020.databinding.MainFragmentBinding

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private val viewModel: MainViewModel by viewModels()
    private lateinit var userListAdapter: UserListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return MainFragmentBinding.inflate(inflater, container, false)
            .apply {
                this.viewModel = this@MainFragment.viewModel
                lifecycleOwner = viewLifecycleOwner

                list.run {
                    layoutManager = LinearLayoutManager(context)
                    addItemDecoration(
                        DividerItemDecoration(
                            context,
                            DividerItemDecoration.VERTICAL
                        )
                    )

                    adapter =
                        UserListAdapter(viewLifecycleOwner, this@MainFragment.viewModel).also {
                            userListAdapter = it
                        }

                }
            }
            .run {
                root
            }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.run {
            users.observe(viewLifecycleOwner, {
                userListAdapter.submitList(it)
            })

            toastRequest.observeEvent(viewLifecycleOwner, {
                Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
            })
        }
    }

}