package com.slailati.android.travelappdesign.ui.adapter

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CategoryModel (
    val type: CategoryType,
    val title: String
) : Parcelable

enum class CategoryType(val value: String) {
    HOUSE("House"),
    CAMPING("Camping"),
    VILLA("Villa"),
    HOTEL("Hotel")
}