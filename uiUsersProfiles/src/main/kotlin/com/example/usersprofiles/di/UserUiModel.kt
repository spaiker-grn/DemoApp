package com.example.usersprofiles.di

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import com.example.uicommon.mvvm.di.koin.fragmentFactory
import com.example.uicommon.mvvm.di.koin.viewModelFactory
import com.example.usersprofiles.ProfileSharedViewModelImpl
import com.example.usersprofiles.birthday.BirthdayFragment
import com.example.usersprofiles.birthday.BirthdayViewModelImpl
import com.example.usersprofiles.body.BodyWeightFragment
import com.example.usersprofiles.body.BodyWeightViewModelImpl
import com.example.usersprofiles.image.EditImageFragment
import com.example.usersprofiles.image.EditImageViewModelImpl
import com.example.usersprofiles.usersprofiles.UsersProfilesFragment
import com.example.usersprofiles.usersprofiles.UsersProfilesViewModelImpl
import org.koin.core.parameter.parametersOf
import org.koin.dsl.module

val usersProfileModule = module {

    viewModelFactory { fragmentActivity: FragmentActivity, _: Fragment ->
        BodyWeightViewModelImpl(
            profileSharedViewModel = ViewModelProvider(fragmentActivity).get<ProfileSharedViewModelImpl>(),
            navControllerProvider = get()
        )
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

    viewModelFactory { fragmentActivity: FragmentActivity, _: Fragment ->
        UsersProfilesViewModelImpl(
            profileSharedViewModel = ViewModelProvider(fragmentActivity).get<ProfileSharedViewModelImpl>(),
            navControllerProvider = get()
        )
    }

    fragmentFactory { activity: FragmentActivity ->
        UsersProfilesFragment(
            imageProvider = get(),
            viewModelProvider = { fragment ->
                val viewModelFactory: ViewModelProvider.Factory =
                    get { parametersOf(activity, fragment) }
                ViewModelProvider(fragment, viewModelFactory)
                    .get<UsersProfilesViewModelImpl>()
            }
        )
    }

    viewModelFactory { fragmentActivity: FragmentActivity, _: Fragment ->
        EditImageViewModelImpl(
            profileSharedViewModel = ViewModelProvider(fragmentActivity).get<ProfileSharedViewModelImpl>(),
            navControllerProvider = get()
        )
    }

    fragmentFactory { activity: FragmentActivity ->
        EditImageFragment(
            imageProvider = get(),
            viewModelProvider = { fragment ->
                val viewModelFactory: ViewModelProvider.Factory =
                    get { parametersOf(activity, fragment) }

                ViewModelProvider(fragment, viewModelFactory)
                    .get<EditImageViewModelImpl>()
            }
        )
    }

    viewModelFactory { fragmentActivity: FragmentActivity, _: Fragment ->
        BirthdayViewModelImpl(
            profileSharedViewModel = ViewModelProvider(fragmentActivity).get<ProfileSharedViewModelImpl>(),
            navControllerProvider = get()
        )
    }

    fragmentFactory { activity: FragmentActivity ->
        BirthdayFragment(
            viewModelProvider = { fragment ->
                val viewModelFactory: ViewModelProvider.Factory =
                    get { parametersOf(activity, fragment) }

                ViewModelProvider(fragment, viewModelFactory)
                    .get<BirthdayViewModelImpl>()
            }
        )
    }
}