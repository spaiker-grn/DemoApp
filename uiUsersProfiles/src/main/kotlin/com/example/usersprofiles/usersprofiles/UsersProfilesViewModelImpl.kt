package com.example.usersprofiles.usersprofiles

import androidx.lifecycle.viewModelScope
import com.example.uicommon.mvvm.BaseViewModelImpl
import com.example.uicommon.mvvm.NavControllerProvider
import com.example.usersprofiles.ProfileSharedViewModel
import com.example.usersprofiles.model.UserCardModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class UsersProfilesViewModelImpl(
    private val profileSharedViewModel: ProfileSharedViewModel,
    private val navControllerProvider: NavControllerProvider
) : BaseViewModelImpl(), UsersProfilesViewModel {

    private var usersState = emptyList<UserCardModel>()

    private val _viewState = MutableStateFlow(emptyList<UserCardModel>())

    override val viewState: Flow<List<UserCardModel>> = _viewState

    init {
        viewModelScope.launch {
            profileSharedViewModel.updateStateFlow.collect { updatedState ->
                val index = usersState.indexOfFirst { it.userId == updatedState.userId }

                usersState = usersState.toMutableList().apply {
                    if (index != -1) {
                        removeAt(index)
                        add(index, updatedState)
                    } else {
                        add(updatedState)
                    }

                    _viewState.emit(this)
                }
            }
        }
    }

    override val updateStateFlow: Flow<UserCardModel>
        get() = profileSharedViewModel.updateStateFlow

    override fun updateUser(userCardModel: UserCardModel) {
        profileSharedViewModel.updateUser(userCardModel)
        navControllerProvider.navigateTo(UsersProfilesFragmentDirections.actionToEditBody())
    }

}