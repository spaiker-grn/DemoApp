package com.example.usersprofiles.body

import androidx.fragment.app.Fragment
import com.example.uicommon.mvvm.BaseFragment
import com.example.uicommon.mvvm.FragmentViewBinding
import com.example.usersprofiles.R
import com.example.usersprofiles.databinding.BodyWeightLayoutBinding
import com.example.usersprofiles.databinding.NavigationMergeBinding
import com.example.usersprofiles.model.WeightModel
import com.example.usersprofiles.model.WeightUnit

class BodyWeightFragment(
    viewModelProvider: (fragment: Fragment) -> BodyWeightViewModel
) : BaseFragment<BodyWeightViewModel, BodyWeightLayoutBinding>(
    layoutId = R.layout.body_weight_layout,
    viewModelProvider
) {
    override val toolbarTitle: String by lazy { getString(com.example.uicommon.R.string.BODY_WEIGHT_SCREEN_TITLE) }
    private var mergeBinding: NavigationMergeBinding? = null

    override val initViewBinding: () -> FragmentViewBinding<BodyWeightLayoutBinding> = {
        FragmentViewBinding(
            bind = {
                mergeBinding = NavigationMergeBinding.bind(it)
                BodyWeightLayoutBinding.bind(it)
            },
            initViews = ::initViews,
            clearViews = ::clearViews
        )
    }

    private fun initViews(binding: BodyWeightLayoutBinding) {
        mergeBinding?.nextButton?.setOnClickListener {
            viewModel.onComplete(getWeightModel(binding))
        }
        binding.apply {
            numberPicker.apply {
                minValue = 0
                maxValue = 400
                wrapSelectorWheel = true
            }
        }

        setWeightModel(binding, viewModel.viewState)
    }

    private fun setWeightModel(binding: BodyWeightLayoutBinding, weightModel: WeightModel?) {
        binding.numberPicker.value = weightModel?.value ?: 0

        when (weightModel?.weightUnit) {
            WeightUnit.LB -> binding.lbRadioButton.isChecked = true
            WeightUnit.KL -> binding.kgRadioButton.isChecked = true
            else -> binding.kgRadioButton.isChecked = true
        }
    }

    private fun getWeightModel(binding: BodyWeightLayoutBinding): WeightModel {
        return WeightModel(
            binding.numberPicker.value,
            when {
                binding.lbRadioButton.isChecked -> WeightUnit.LB
                binding.kgRadioButton.isChecked -> WeightUnit.KL
                else -> WeightUnit.KL
            }
        )
    }

    private fun clearViews(binding: BodyWeightLayoutBinding) {
        mergeBinding?.nextButton?.setOnClickListener(null)
    }

}
