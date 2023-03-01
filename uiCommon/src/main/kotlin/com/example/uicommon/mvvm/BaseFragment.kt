package com.example.uicommon.mvvm

import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding


abstract class BaseFragment<VM : BaseViewModel, VB : ViewBinding>(
    @LayoutRes layoutId: Int,
    viewModelProvider: (Fragment) -> VM
): Fragment(layoutId) {

    abstract val toolbarTitle: String?

    protected val viewModel by lazy { viewModelProvider(this) }

    abstract val fragmentViewBinding: FragmentViewBinding<VB>

    protected val fragmentBinding: VB?
        get() = fragmentViewBinding.get()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fragmentViewBinding.onViewCreated(view)
    }

    override fun onResume() {
        super.onResume()

        this.activity?.title = toolbarTitle
    }

    override fun onDestroyView() {
        super.onDestroyView()

        fragmentViewBinding.onDestroyView()
    }
}