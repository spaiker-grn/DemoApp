package com.example.usersprofiles

import com.example.usersprofiles.model.BirthdayModel
import com.example.usersprofiles.model.ImageModel
import com.example.usersprofiles.model.WeightModel

data class UpdatedUser(
    val userId: String,
    val weightModel: WeightModel? = null,
    val birthDate: BirthdayModel? = null,
    val image: ImageModel? = null
)