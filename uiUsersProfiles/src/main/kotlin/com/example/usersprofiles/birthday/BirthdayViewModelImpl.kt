package com.example.usersprofiles.birthday

import com.example.uicommon.mvvm.BaseViewModelImpl
import com.example.uicommon.mvvm.NavControllerProvider
import com.example.usersprofiles.ProfileSharedViewModel
import com.example.usersprofiles.model.BirthdayModel

class BirthdayViewModelImpl(
    private val profileSharedViewModel: ProfileSharedViewModel,
    private val navControllerProvider: NavControllerProvider,
) : BaseViewModelImpl(), BirthdayViewModel {

    override fun onComplete(birthdayModel: BirthdayModel) {
        profileSharedViewModel.updateBirthday(birthdayModel)
        navControllerProvider.navigateTo(BirthdayFragmentDirections.actionToEditImage())
    }
}