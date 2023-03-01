package com.example.uicommon.mvvm

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableSharedFlow

open class BaseViewModelImpl : ViewModel(), BaseViewModel {
    override val loading = MutableSharedFlow<Boolean>()
}