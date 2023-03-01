package com.example.usersprofiles.body

import androidx.fragment.app.Fragment
import com.example.uicommon.mvvm.BaseFragment
import com.example.uicommon.mvvm.FragmentViewBinding
import com.example.usersprofiles.R
import com.example.usersprofiles.databinding.BodyWeightLayoutBinding

class BodyWeightFragment(
    viewModelProvider: (fragment: Fragment) -> BodyWeightViewModel
) : BaseFragment<BodyWeightViewModel, BodyWeightLayoutBinding>(
    layoutId = R.layout.body_weight_layout,
    viewModelProvider
) {

    override val fragmentViewBinding: FragmentViewBinding<BodyWeightLayoutBinding> =
        FragmentViewBinding(
            bind = { BodyWeightLayoutBinding.bind(it) },
            initViews = ::initViews
        )

    private fun initViews(usersScreenLayoutBinding: BodyWeightLayoutBinding) {

    }

}
