package com.example.usersprofiles.body

import com.example.uicommon.mvvm.BaseViewModelImpl
import com.example.uicommon.mvvm.NavControllerProvider
import com.example.usersprofiles.ProfileSharedViewModel
import com.example.usersprofiles.model.WeightModel

class BodyWeightViewModelImpl(
    private val profileSharedViewModel: ProfileSharedViewModel,
    private val navControllerProvider: NavControllerProvider
) : BaseViewModelImpl(), BodyWeightViewModel {

    override val viewState: WeightModel?
        get() = profileSharedViewModel.userState?.weight

    override fun onComplete(weightModel: WeightModel) {
        profileSharedViewModel.updateWeight(weightModel)
        navControllerProvider.navigateTo(BodyWeightFragmentDirections.actionToEditBirthday())
    }
}