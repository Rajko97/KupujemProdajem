package com.quable.kupujemprodajem.features.single

import com.quable.domain.models.AdDetails

data class AdDetailsView(
    val adId: String,
    val locationName: String,
    val categoryName: String,
    val groupName: String,
    val description: String,
    val photos: String,
) {
    constructor() : this("", "", "", "", "", "")
}

fun AdDetails.toView() =
    AdDetailsView(
        adId = adId,
        locationName = locationName,
        categoryName = categoryName,
        groupName = groupName,
        description = description,
        photos = "/$photos",
    )
