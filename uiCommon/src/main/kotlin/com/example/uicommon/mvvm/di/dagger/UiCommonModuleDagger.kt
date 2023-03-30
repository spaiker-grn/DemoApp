package com.example.uicommon.mvvm.di.dagger

import com.example.uicommon.mvvm.ImageProvider
import com.example.uicommon.mvvm.ImageProviderImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class UiCommonModule {

    @Provides
    @Singleton
    fun provideImageProvider(): ImageProviderImpl = ImageProviderImpl()
}

@Module
interface UiCommonBinds {

    @Binds
    fun bindImageProvider(imageProviderImpl: ImageProviderImpl): ImageProvider
}