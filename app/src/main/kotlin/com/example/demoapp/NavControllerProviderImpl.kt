package com.example.demoapp

import android.app.Activity
import android.app.Application
import android.os.Bundle
import android.util.Log
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.fragment.NavHostFragment
import com.example.uicommon.mvvm.NavControllerProvider

class NavControllerProviderImpl: NavControllerProvider {
    private var navigationActivity: MainActivity? = null

    override fun get(): NavController? =
        navigationActivity?.supportFragmentManager?.let {
            (it.findFragmentById(R.id.navHostFragment) as NavHostFragment).navController
        }

    override fun navigateTo(direction: NavDirections) {
        try {
            get()?.navigate(direction)
        } catch (throwable: Throwable) {
            Log.d("NavigationError", "$throwable")
        }
    }

    override fun navigateUp() {
        try {
            get()?.navigateUp()
        } catch (throwable: Throwable) {
            Log.d("NavigationError", "$throwable")
        }
    }

    fun onApplicationCreate(application: Application) {
        application.registerActivityLifecycleCallbacks(
            object : Application.ActivityLifecycleCallbacks {
                override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
                    if (activity is MainActivity) {
                        navigationActivity = activity
                    }
                }

                override fun onActivityResumed(activity: Activity) {
                    if (activity is MainActivity) {
                        navigationActivity = activity
                    }
                }

                override fun onActivityDestroyed(activity: Activity) {
                    if (activity == navigationActivity) {
                        navigationActivity = null
                    }
                }

                override fun onActivityPaused(activity: Activity) = Unit
                override fun onActivityStarted(activity: Activity) = Unit
                override fun onActivityStopped(activity: Activity) = Unit
                override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) =
                    Unit
            }
        )
    }
}