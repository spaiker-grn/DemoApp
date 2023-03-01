package com.example.uicommon.mvvm.di

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentFactory
import org.koin.core.Koin
import org.koin.core.instance.InstanceFactory
import org.koin.core.module.Module
import org.koin.core.parameter.parametersOf
import org.koin.core.scope.Scope

class KoinFragmentFactory(
    private val activity: FragmentActivity,
    private val koin: Koin
) : FragmentFactory() {

    @Suppress("TooGenericExceptionCaught")
    override fun instantiate(classLoader: ClassLoader, className: String): Fragment =
        try {
            val clazz = loadFragmentClass(classLoader, className)
            koin.get(
                clazz = clazz.kotlin,
                qualifier = null,
                parameters = { parametersOf(activity) }
            )
        } catch (koinThrowable: Throwable) {
            try {
                super.instantiate(classLoader, className)
            } catch (_: Throwable) {
                throw koinThrowable
            }
        }
}

inline fun <reified T : Fragment> Module.fragmentFactory(
    noinline definition: FragmentDefinition<T>
): Pair<Module, InstanceFactory<T>> =
    factory { (activity: FragmentActivity) ->
        definition(activity)
    }

typealias FragmentDefinition<T> = Scope.(FragmentActivity) -> T