package com.example.usersprofiles.birthday

import com.example.uicommon.mvvm.BaseViewModel
import com.example.usersprofiles.model.BirthdayModel

interface BirthdayViewModel : BaseViewModel {

    val viewState: BirthdayModel?
    fun onComplete(birthdayModel: BirthdayModel)
}