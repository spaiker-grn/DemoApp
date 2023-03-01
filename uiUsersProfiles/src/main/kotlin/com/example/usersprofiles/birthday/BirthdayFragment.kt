package com.example.usersprofiles.birthday

import androidx.fragment.app.Fragment
import com.example.uicommon.mvvm.BaseFragment
import com.example.uicommon.mvvm.FragmentViewBinding
import com.example.usersprofiles.R
import com.example.usersprofiles.databinding.BirthdayLayoutBinding
import com.example.usersprofiles.databinding.NavigationMergeBinding
import com.example.usersprofiles.model.BirthdayModel
import java.util.*

class BirthdayFragment(
    viewModelProvider: (Fragment) -> BirthdayViewModel
) : BaseFragment<BirthdayViewModel, BirthdayLayoutBinding>(
    R.layout.birthday_layout,
    viewModelProvider = viewModelProvider
) {

    override val toolbarTitle: String by lazy { getString(com.example.uicommon.R.string.BIRTH_DAY_SCREEN_TITLE) }
    private var mergeBinding: NavigationMergeBinding? = null

    override val fragmentViewBinding: FragmentViewBinding<BirthdayLayoutBinding> =
        FragmentViewBinding(
            bind = {
                mergeBinding = NavigationMergeBinding.bind(it)
                BirthdayLayoutBinding.bind(it)
            },
            initViews = ::initViews,
            clearViews = ::clearViews
        )

    private fun clearViews(birthdayLayoutBinding: BirthdayLayoutBinding) {
        mergeBinding?.nextButton?.setOnClickListener(null)
    }

    private fun initViews(birthdayLayoutBinding: BirthdayLayoutBinding) {
        birthdayLayoutBinding.apply {
            birthdayPicker.apply {
                maxDate = Calendar.getInstance().apply { add(Calendar.YEAR, -6) }.timeInMillis
            }
        }

        mergeBinding?.nextButton?.setOnClickListener {
            viewModel.onComplete(getBirthdayModel(birthdayLayoutBinding))
        }
    }

    private fun getBirthdayModel(birthdayLayoutBinding: BirthdayLayoutBinding) =
        birthdayLayoutBinding.birthdayPicker.run {
            BirthdayModel(
                Date(
                    Calendar.Builder().setDate(year, month, dayOfMonth).build().timeInMillis
                )
            )
        }
}