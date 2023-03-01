package com.example.demoapp

import android.app.Activity
import android.app.Application
import android.os.Bundle

class OnActivityCreatedCallback (
    private val callbacks: (Activity) -> Unit
) : Application.ActivityLifecycleCallbacks {

    override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
        callbacks.invoke(activity)
    }

    override fun onActivityPaused(activity: Activity) = Unit

    override fun onActivityStarted(activity: Activity) = Unit

    override fun onActivityDestroyed(activity: Activity) = Unit

    override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) = Unit

    override fun onActivityStopped(activity: Activity) = Unit

    override fun onActivityResumed(activity: Activity) = Unit
}