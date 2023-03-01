package com.example.usersprofiles.image

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.uicommon.mvvm.BaseFragment
import com.example.uicommon.mvvm.FragmentViewBinding
import com.example.uicommon.mvvm.ImageProvider
import com.example.uicommon.mvvm.extensions.ContinuationStartActivityInput
import com.example.uicommon.mvvm.extensions.registerForStartActivityResult
import com.example.usersprofiles.R
import com.example.usersprofiles.databinding.EditImageLayoutBinding
import com.example.usersprofiles.databinding.NavigationMergeBinding
import kotlin.coroutines.suspendCoroutine


class EditImageFragment(
    private val imageProvider: ImageProvider,
    viewModelProvider: (fragment: Fragment) -> EditImageViewModel
) : BaseFragment<EditImageViewModel, EditImageLayoutBinding>(
    R.layout.edit_image_layout,
    viewModelProvider
) {

    private lateinit var activityResultRegistration: ActivityResultLauncher<ContinuationStartActivityInput>

    private val pickVisualMediaIntent: Intent by lazy {
        ActivityResultContracts.PickVisualMedia().createIntent(
            requireNotNull(context),
            PickVisualMediaRequest(
                ActivityResultContracts.PickVisualMedia.ImageOnly
            )
        )
    }

    override val toolbarTitle: String by lazy { getString(com.example.uicommon.R.string.BIRTH_DAY_SCREEN_TITLE) }
    private var mergeBinding: NavigationMergeBinding? = null

    override val fragmentViewBinding: FragmentViewBinding<EditImageLayoutBinding> =
        FragmentViewBinding(
            bind = {
                mergeBinding = NavigationMergeBinding.bind(it)
                EditImageLayoutBinding.bind(it)
            },
            initViews = ::initView,
            clearViews = ::clearView
        )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityResultRegistration = registerForStartActivityResult()

        lifecycleScope.launchWhenStarted {
            viewModel.viewState.collect { changeState(it) }
        }
    }

    private fun changeState(imageUri: Uri) {
        activity?.contentResolver?.apply {
            fragmentBinding?.pickImageView?.setImageBitmap(
                imageProvider.provideImage(imageUri, this)
            )
        }
    }

    private fun initView(binding: EditImageLayoutBinding) {
        binding.apply {
            pickImageButton.setOnClickListener {
                viewModel.onPickImageClick(getPickImageRequest())
            }
            mergeBinding?.nextButton?.setOnClickListener {
                viewModel.onComplete()
            }
        }
    }

    private fun getPickImageRequest(): suspend () -> ActivityResult? = {
        suspendCoroutine { continuation ->
            activityResultRegistration.launch(
                ContinuationStartActivityInput(
                    pickVisualMediaIntent,
                    continuation
                )
            )
        }
    }

    private fun clearView(binding: EditImageLayoutBinding) {
        binding.apply {
            pickImageButton.setOnClickListener(null)
            mergeBinding?.nextButton?.setOnClickListener(null)
        }
    }
}