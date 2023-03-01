package com.example.usersprofiles.usersprofiles

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.uicommon.mvvm.BaseFragment
import com.example.uicommon.mvvm.FragmentViewBinding
import com.example.usersprofiles.R
import com.example.usersprofiles.databinding.UsersScreenLayoutBinding

class UsersProfilesFragment(
    viewModelProvider: (fragment: Fragment) -> UsersProfilesViewModel
) : BaseFragment<UsersProfilesViewModel, UsersScreenLayoutBinding>(
    layoutId = R.layout.users_screen_layout,
    viewModelProvider
) {

    override val fragmentViewBinding: FragmentViewBinding<UsersScreenLayoutBinding> =
        FragmentViewBinding(
            bind = { UsersScreenLayoutBinding.bind(it) },
            initViews = ::initViews
        )

    private fun initViews(usersScreenLayoutBinding: UsersScreenLayoutBinding) {

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launchWhenStarted {
            viewModel.viewState.collect {
                fragmentBinding?.userTextView?.text = it
            }
        }
    }
}