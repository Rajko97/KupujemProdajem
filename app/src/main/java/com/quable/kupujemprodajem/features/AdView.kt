package com.quable.kupujemprodajem.features

import com.quable.domain.models.Ad

data class AdView(
    val id: Long,
    val posted: String,
    val locationName: String,
    val name: String,
    val price: String,
    val currency: String,
    val priceFixed: String,
    val photoThumbnailUrl: String,
    val favoriteCount: Int,
    val isFollowingAd: Boolean,
)

fun Ad.toView() = AdView(
    id = id,
    posted = posted,
    locationName = locationName,
    name = name,
    price = price,
    currency = currency,
    priceFixed = priceFixed,
    photoThumbnailUrl = photoThumbnailUrl,
    favoriteCount = favoriteCount,
    isFollowingAd = isFollowingAd,
)
