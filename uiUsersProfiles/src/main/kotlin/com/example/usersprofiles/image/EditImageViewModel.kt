package com.example.usersprofiles.image

import android.net.Uri
import androidx.activity.result.ActivityResult
import com.example.uicommon.mvvm.BaseViewModel
import kotlinx.coroutines.flow.Flow

interface EditImageViewModel : BaseViewModel {
    val viewState: Flow<Uri>
    fun onComplete()
    fun onPickImageClick(action: suspend () -> ActivityResult?)
}