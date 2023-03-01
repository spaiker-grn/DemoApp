package com.example.usersprofiles.body

import com.example.uicommon.mvvm.BaseViewModel
import com.example.usersprofiles.model.WeightModel

interface BodyWeightViewModel: BaseViewModel {
    fun onComplete(weightModel: WeightModel)
}