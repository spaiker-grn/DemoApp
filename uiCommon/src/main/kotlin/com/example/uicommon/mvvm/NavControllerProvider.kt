package com.example.uicommon.mvvm

import androidx.navigation.NavController
import androidx.navigation.NavDirections

interface NavControllerProvider {
    fun get(): NavController?
    fun navigateTo(direction: NavDirections)
    fun navigateUp()
}