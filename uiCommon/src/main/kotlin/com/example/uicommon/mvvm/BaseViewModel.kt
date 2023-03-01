package com.example.uicommon.mvvm

import kotlinx.coroutines.flow.Flow

interface BaseViewModel {
    val loading: Flow<Boolean>
}