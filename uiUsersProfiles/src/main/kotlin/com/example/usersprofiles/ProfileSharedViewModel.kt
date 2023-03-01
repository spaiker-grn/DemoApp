package com.example.usersprofiles

import com.example.uicommon.mvvm.BaseViewModel
import com.example.usersprofiles.model.BirthdayModel
import com.example.usersprofiles.model.ImageModel
import com.example.usersprofiles.model.WeightModel
import kotlinx.coroutines.flow.Flow

interface ProfileSharedViewModel : BaseViewModel {

    val updateStateFlow: Flow<UpdatedUser>

    fun updateUser(userId: String)
    fun updateImageUri(imageModel: ImageModel)
    fun updateWeight(weightModel: WeightModel)
    fun updateBirthday(birthdayModel: BirthdayModel)
    fun completeUpdate()
}