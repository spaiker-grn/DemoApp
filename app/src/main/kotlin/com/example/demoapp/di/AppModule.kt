package com.example.demoapp.di

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import com.example.demoapp.NavControllerProviderImpl
import com.example.uicommon.mvvm.NavControllerProvider
import com.example.uicommon.mvvm.di.KoinViewModelFactory
import org.koin.dsl.module

val appModule = module {

    factory<ViewModelProvider.Factory> { (
        activity: FragmentActivity,
        fragment: Fragment,
    ) ->
        KoinViewModelFactory(
            activity = activity, fragment = fragment, scope = this
        )
    }

    single<NavControllerProvider> {
        NavControllerProviderImpl()
    }
}