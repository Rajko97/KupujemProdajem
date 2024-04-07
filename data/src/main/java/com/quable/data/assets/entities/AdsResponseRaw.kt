package com.quable.data.assets.entities

import com.google.gson.annotations.SerializedName
import com.quable.domain.models.AdsResponse

data class AdsResponseRaw(
    @SerializedName("pages") val pages: Int,
    @SerializedName("page") val page: Int,
    @SerializedName("ads") val ads: List<AdRaw>,
)

fun AdsResponseRaw.toDomain() = AdsResponse(
    pages = pages,
    page = page,
    ads = ads.map { it.toDomain() },
)
