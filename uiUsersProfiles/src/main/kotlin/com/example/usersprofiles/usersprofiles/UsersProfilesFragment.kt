package com.example.usersprofiles.usersprofiles

import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.uicommon.mvvm.BaseFragment
import com.example.uicommon.mvvm.FragmentViewBinding
import com.example.uicommon.mvvm.ImageProvider
import com.example.uicommon.mvvm.adapter.FingerprintAdapter
import com.example.uicommon.mvvm.adapter.ItemFingerprint
import com.example.usersprofiles.R
import com.example.usersprofiles.databinding.UsersScreenLayoutBinding
import com.example.usersprofiles.model.UserCardModel
import com.google.android.material.divider.MaterialDividerItemDecoration

class UsersProfilesFragment(
    private val imageProvider: ImageProvider,
    viewModelProvider: (fragment: Fragment) -> UsersProfilesViewModel
) : BaseFragment<UsersProfilesViewModel, UsersScreenLayoutBinding>(
    layoutId = R.layout.users_screen_layout,
    viewModelProvider
) {
    override val toolbarTitle: String by lazy { getString(com.example.uicommon.R.string.MAIN_SCREEN_TITLE) }

    override val fragmentViewBinding: FragmentViewBinding<UsersScreenLayoutBinding> =
        FragmentViewBinding(
            bind = { UsersScreenLayoutBinding.bind(it) },
            initViews = ::initViews,
            clearViews = ::clearViews
        )

    private val usersAdapter = FingerprintAdapter(getFingerprints())
    private fun initViews(binding: UsersScreenLayoutBinding) {
        binding.apply {
            usersRecyclerView.apply {
                layoutManager = LinearLayoutManager(context)
                adapter = usersAdapter
                addItemDecoration(MaterialDividerItemDecoration(context, LinearLayout.VERTICAL))
            }

            addNewUserButton.setOnClickListener {
                viewModel.updateUser(
                    UserCardModel(
                        userId = System.currentTimeMillis().toString(),
                        image = null,
                        birthDate = null,
                        weight = null
                    )
                )
            }
        }
    }

    private fun clearViews(binding: UsersScreenLayoutBinding) {
        binding.addNewUserButton.setOnClickListener(null)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycleScope.launchWhenStarted {
            viewModel.viewState.collect {
                changeViewState(it)
            }
        }
    }

    private fun changeViewState(usersList: List<UserCardModel>) {
        fragmentBinding?.apply {
            if (usersList.isEmpty()) {
                usersRecyclerView.isVisible = false
                emptyStateTextView.isVisible = true
            } else {
                usersRecyclerView.isVisible = true
                emptyStateTextView.isVisible = false
                usersAdapter.submitList(usersList)
            }
        }
    }

    private fun getFingerprints(): List<ItemFingerprint<*, *>> =
        listOf(UserFingerprint(imageProvider) { viewModel.updateUser(it) })
}