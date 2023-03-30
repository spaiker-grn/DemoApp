package com.example.uicommon.mvvm.di.koin

import com.example.uicommon.mvvm.ImageProvider
import com.example.uicommon.mvvm.ImageProviderImpl
import org.koin.dsl.module

val uiCommonModule = module {
    factory<ImageProvider> {
        ImageProviderImpl()
    }
}