package com.example.uicommon.mvvm.di.dagger

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory

class DaggerFragmentFactory() : FragmentFactory() {

    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {


        return super.instantiate(classLoader, className)
    }
}