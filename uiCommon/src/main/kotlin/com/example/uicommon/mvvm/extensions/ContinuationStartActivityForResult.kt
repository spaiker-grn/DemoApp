package com.example.uicommon.mvvm.extensions

import android.content.Context
import android.content.Intent
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import kotlin.coroutines.Continuation

class ContinuationStartActivityForResult :
    ActivityResultContract<ContinuationStartActivityInput, ContinuationStartActivityResult>() {

    private val startActivityForResult = ActivityResultContracts.StartActivityForResult()

    private var continuation: Continuation<ActivityResult>? = null

    override fun createIntent(context: Context, input: ContinuationStartActivityInput): Intent {
        continuation = input.continuation
        return startActivityForResult.createIntent(context, input.intent)
    }

    override fun parseResult(resultCode: Int, intent: Intent?): ContinuationStartActivityResult {
        val result = startActivityForResult.parseResult(resultCode, intent)
        return ContinuationStartActivityResult(result, continuation)
            .also { continuation = null }
    }
}

class ContinuationStartActivityInput(
    val intent: Intent,
    val continuation: Continuation<ActivityResult>
)

class ContinuationStartActivityResult(
    val result: ActivityResult,
    val continuation: Continuation<ActivityResult>?
)
