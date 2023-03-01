package com.example.demoapp

import android.app.Application
import androidx.fragment.app.FragmentActivity
import com.example.demoapp.di.appModule
import com.example.uicommon.mvvm.NavControllerProvider
import com.example.uicommon.mvvm.di.KoinFragmentFactory
import com.example.uicommon.mvvm.di.uiCommonModule
import com.example.usersprofiles.di.usersProfileModule
import org.koin.android.ext.android.getKoin
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class DemoAppApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(applicationContext)
            modules(
                listOf(
                    appModule,
                    usersProfileModule,
                    uiCommonModule
                )
            )
        }

        (getKoin().get<NavControllerProvider>() as NavControllerProviderImpl)
            .onApplicationCreate(this)

        registerActivityLifecycleCallbacks(onActivityCreatedLifecycleCallback)
    }

    private val onActivityCreatedLifecycleCallback = OnActivityCreatedCallback { activity ->
        (activity as? FragmentActivity)?.let { fragmentActivity ->
            val fragmentFactory = KoinFragmentFactory(
                activity = fragmentActivity,
                koin = getKoin()
            )

            fragmentActivity.supportFragmentManager.fragmentFactory = fragmentFactory
        }
    }
}