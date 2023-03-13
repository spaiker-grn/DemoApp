package com.example.usersprofiles.image

import androidx.activity.result.ActivityResult
import com.example.uicommon.mvvm.BaseViewModel
import com.example.usersprofiles.model.ImageModel
import kotlinx.coroutines.flow.Flow

interface EditImageViewModel : BaseViewModel {
    val viewState: Flow<ImageModel>
    fun onComplete()
    fun onPickImageClick(action: suspend () -> ActivityResult?)
}