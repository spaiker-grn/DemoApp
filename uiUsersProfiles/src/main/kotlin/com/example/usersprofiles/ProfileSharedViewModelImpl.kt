package com.example.usersprofiles

import androidx.lifecycle.viewModelScope
import com.example.uicommon.mvvm.BaseViewModelImpl
import com.example.usersprofiles.model.BirthdayModel
import com.example.usersprofiles.model.ImageModel
import com.example.usersprofiles.model.UserCardModel
import com.example.usersprofiles.model.WeightModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch

class ProfileSharedViewModelImpl : BaseViewModelImpl(), ProfileSharedViewModel {

    private var lastUpdateState: UserCardModel? = null

    private val _updateStateFlow = MutableSharedFlow<UserCardModel>()

    override val updateStateFlow: Flow<UserCardModel> = _updateStateFlow

    override val userState: UserCardModel?
        get() = lastUpdateState

    override fun updateUser(userCardModel: UserCardModel) {
        lastUpdateState = userCardModel
    }

    override fun updateImageUri(imageModel: ImageModel) {
        lastUpdateState = lastUpdateState?.copy(image = imageModel)
    }

    override fun updateWeight(weightModel: WeightModel) {
        lastUpdateState = lastUpdateState?.copy(weight = weightModel)
    }

    override fun updateBirthday(birthdayModel: BirthdayModel) {
        lastUpdateState = lastUpdateState?.copy(birthDate = birthdayModel)
    }

    override fun completeUpdate() {
        lastUpdateState?.let {
            viewModelScope.launch {
                _updateStateFlow.emit(it)
            }
        }
    }
}