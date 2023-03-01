package com.example.uicommon.mvvm

import android.content.ContentResolver
import android.graphics.Bitmap
import android.net.Uri

interface ImageProvider {

    fun provideImage(imageUri: Uri, contentResolver: ContentResolver): Bitmap?
}