package com.quable.domain.models

data class Ad(
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
