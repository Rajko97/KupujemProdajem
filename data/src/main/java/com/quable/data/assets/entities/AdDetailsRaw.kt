package com.quable.data.assets.entities

import com.google.gson.annotations.SerializedName
import com.quable.domain.models.AdDetails

data class AdDetailsRaw(
    @SerializedName("ad_id") val adId: String,
    @SerializedName("location_name") val locationName: String,
    @SerializedName("cateogry_name") val categoryName: String,
    @SerializedName("group_name") val groupName: String,
    @SerializedName("description") val description: String,
    @SerializedName("photos") val photos: String,
)

fun AdDetailsRaw.toDomain() = AdDetails(
    adId = adId,
    locationName = locationName,
    categoryName = categoryName,
    groupName = groupName,
    description = description,
    photos = photos,
)
