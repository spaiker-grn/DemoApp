package com.example.usersprofiles.usersprofiles

import com.example.uicommon.mvvm.BaseViewModel
import com.example.usersprofiles.UpdatedUser
import com.example.usersprofiles.model.UserCardModel
import kotlinx.coroutines.flow.Flow

interface UsersProfilesViewModel : BaseViewModel {

    val viewState: Flow<List<UserCardModel>>

    val updateStateFlow: Flow<UpdatedUser>

    fun updateUser(userId: String)

}