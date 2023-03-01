package com.example.usersprofiles.di

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import com.example.uicommon.mvvm.di.fragmentFactory
import com.example.uicommon.mvvm.di.viewModelFactory
import com.example.usersprofiles.body.BodyWeightFragment
import com.example.usersprofiles.body.BodyWeightViewModelImpl
import com.example.usersprofiles.usersprofiles.UsersProfilesFragment
import com.example.usersprofiles.usersprofiles.UsersProfilesViewModelImpl
import org.koin.core.parameter.parametersOf
import org.koin.dsl.module

val usersProfileModule = module {

    viewModelFactory { _: FragmentActivity, _: Fragment ->
        BodyWeightViewModelImpl()
    }

    fragmentFactory { activity: FragmentActivity ->
        BodyWeightFragment(
            viewModelProvider = { fragment ->
                val viewModelFactory: ViewModelProvider.Factory =
                    get { parametersOf(activity, fragment) }
                ViewModelProvider(fragment, viewModelFactory)
                    .get<BodyWeightViewModelImpl>()
            }
        )
    }

    viewModelFactory { _: FragmentActivity, _: Fragment ->
        UsersProfilesViewModelImpl()
    }

    fragmentFactory { activity: FragmentActivity ->
        UsersProfilesFragment(
            viewModelProvider = { fragment ->
                val viewModelFactory: ViewModelProvider.Factory =
                    get { parametersOf(activity, fragment) }
                ViewModelProvider(fragment, viewModelFactory)
                    .get<UsersProfilesViewModelImpl>()
            }
        )
    }
}