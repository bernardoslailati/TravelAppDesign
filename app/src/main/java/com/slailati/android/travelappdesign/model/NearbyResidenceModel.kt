package com.slailati.android.travelappdesign.model

data class NearbyResidenceModel (
    val photoDrawableId: Int,
    val name: String,
    val locationName: String,
    val valuePerDay: Float,
    val placeRating: Float,
    val isFavorite: Boolean
)