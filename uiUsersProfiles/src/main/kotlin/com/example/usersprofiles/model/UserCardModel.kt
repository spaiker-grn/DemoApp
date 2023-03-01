package com.example.usersprofiles.model

import android.net.Uri
import com.example.uicommon.mvvm.adapter.ListItem
import com.example.usersprofiles.UpdatedUser
import java.util.*

data class UserCardModel(
    val userId: String,
    val image: ImageModel?,
    val weight: WeightModel?,
    val birthDate: BirthdayModel?,
) : ListItem {
    companion object {
        val mapFromUpdateModel = { updatedUser: UpdatedUser ->
            UserCardModel(
                updatedUser.userId,
                updatedUser.image,
                updatedUser.weightModel,
                updatedUser.birthDate
            )
        }
    }
}

data class ImageModel(
    val imageUri: Uri
)

data class WeightModel(
    val value: Int,
    val weightUnit: WeightUnit
)

enum class WeightUnit {
    KL, LB
}

data class BirthdayModel(
    val birthDate: Date
)