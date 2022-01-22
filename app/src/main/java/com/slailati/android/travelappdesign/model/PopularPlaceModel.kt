package com.slailati.android.travelappdesign.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PopularPlaceModel(
    val photoDrawableId: Int,
    val name: String,
    val rating: Float
) : Parcelable