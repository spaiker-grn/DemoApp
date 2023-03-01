package com.example.usersprofiles.usersprofiles

import com.example.uicommon.mvvm.BaseViewModelImpl
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class UsersProfilesViewModelImpl: BaseViewModelImpl(), UsersProfilesViewModel {

    override val viewState: Flow<String> = flow {
        emit("Demo String")
    }

}