package com.example.uicommon.mvvm.extensions

import androidx.activity.result.ActivityResultLauncher
import androidx.fragment.app.Fragment
import kotlin.coroutines.resume

fun Fragment.registerForStartActivityResult():
        ActivityResultLauncher<ContinuationStartActivityInput> =
    this.registerForActivityResult(
        ContinuationStartActivityForResult()
    ) { result ->
        result.continuation?.resume(result.result)
    }