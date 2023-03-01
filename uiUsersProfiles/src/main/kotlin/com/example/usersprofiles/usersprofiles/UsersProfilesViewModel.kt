package com.example.usersprofiles.usersprofiles

import com.example.uicommon.mvvm.BaseViewModel
import kotlinx.coroutines.flow.Flow

interface UsersProfilesViewModel : BaseViewModel {

    val viewState: Flow<String>

}