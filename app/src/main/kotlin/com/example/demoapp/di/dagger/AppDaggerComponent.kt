package com.example.demoapp.di.dagger

import androidx.fragment.app.FragmentFactory
import com.example.uicommon.mvvm.di.dagger.DaggerFragmentFactory
import com.example.uicommon.mvvm.di.dagger.UiCommonModule
import dagger.Component
import dagger.Module
import dagger.Provides

@Component(modules = [UiCommonModule::class, AppDaggerModule::class])
interface AppDaggerComponent {


}

@Module
class AppDaggerModule {

    @Provides
    fun provideFragmentFactory(): FragmentFactory {
        return DaggerFragmentFactory()
    }
}



