package com.example.usersprofiles.image

import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.viewModelScope
import com.example.uicommon.mvvm.BaseViewModelImpl
import com.example.uicommon.mvvm.NavControllerProvider
import com.example.usersprofiles.ProfileSharedViewModel
import com.example.usersprofiles.model.ImageModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.merge
import kotlinx.coroutines.launch

class EditImageViewModelImpl(
    private val profileSharedViewModel: ProfileSharedViewModel,
    private val navControllerProvider: NavControllerProvider
) : BaseViewModelImpl(), EditImageViewModel {

    private val stateFlow = MutableSharedFlow<ImageModel>()

    override val viewState: Flow<ImageModel> = merge(flow {
        profileSharedViewModel.userState?.image?.let {
            emit(it)
        }
    }, stateFlow)

    override fun onComplete() {
        profileSharedViewModel.completeUpdate()
        navControllerProvider.navigateTo(EditImageFragmentDirections.actionToUsersScreen())
    }

    override fun onPickImageClick(action: suspend () -> ActivityResult?) {
        viewModelScope.launch {
            action.invoke()?.let {
                ActivityResultContracts.PickVisualMedia().parseResult(it.resultCode, it.data)
                    ?.apply {
                        stateFlow.emit(ImageModel(this))
                        profileSharedViewModel.updateImageUri(ImageModel(this))
                    }
            }
        }
    }

}