package com.example.usersprofiles

import com.example.uicommon.mvvm.BaseViewModel
import com.example.usersprofiles.model.BirthdayModel
import com.example.usersprofiles.model.ImageModel
import com.example.usersprofiles.model.UserCardModel
import com.example.usersprofiles.model.WeightModel
import kotlinx.coroutines.flow.Flow

interface ProfileSharedViewModel : BaseViewModel {

    val updateStateFlow: Flow<UserCardModel>
    val userState: UserCardModel?
    fun updateUser(userCardModel: UserCardModel)
    fun updateImageUri(imageModel: ImageModel)
    fun updateWeight(weightModel: WeightModel)
    fun updateBirthday(birthdayModel: BirthdayModel)
    fun completeUpdate()
}