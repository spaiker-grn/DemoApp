package com.example.uicommon.mvvm

import android.content.ContentResolver
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore

class ImageProviderImpl : ImageProvider {

    override fun provideImage(imageUri: Uri, contentResolver: ContentResolver): Bitmap? {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            ImageDecoder.decodeBitmap(
                ImageDecoder.createSource(contentResolver, imageUri)
            )
        } else {
            MediaStore.Images.Media.getBitmap(
                contentResolver, imageUri
            )
        }
    }
}