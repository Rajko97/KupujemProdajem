package com.quable.kupujemprodajem.features

import com.quable.domain.models.Ad

data class AdView(
    val id: Long,
    val posted: String,
    val locationName: String,
    val name: String,
    val price: String,
    val currency: String?,
    val priceFixed: String,
    val photoThumbnailUrl: String,
    val favoriteCount: Int,
    val isFollowingAd: Boolean,
) {
    constructor() : this(0, "2023-04-12 11:16:27", "", "", "", "", "", "", 0, false)

    override fun equals(other: Any?): Boolean {
        if (other is AdView) {
            return this.id == other.id && this.posted == other.posted && this.locationName == other.locationName &&
                this.name == other.name && this.price == other.price && this.currency == other.currency &&
                this.priceFixed == other.priceFixed && this.photoThumbnailUrl == other.photoThumbnailUrl &&
                this.favoriteCount == other.favoriteCount && this.isFollowingAd == other.isFollowingAd
        }
        return super.equals(other)
    }
}

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
