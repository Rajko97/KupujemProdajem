package com.quable.data.assets.entities

import com.google.gson.annotations.SerializedName
import com.quable.domain.models.Ad

data class AdRaw(
    @SerializedName("ad_id") val id: Long,
    @SerializedName("posted") val posted: String,
    @SerializedName("location_name") val locationName: String,
    @SerializedName("name") val name: String,
    @SerializedName("price") val price: String,
    @SerializedName("currency") val currency: String,
    @SerializedName("price_fixed") val priceFixed: String,
    @SerializedName("photo1_tmb_300x300") val photoThumbnailUrl: String,
    @SerializedName("favorite_count") val favoriteCount: Int,
    @SerializedName("is_following_ad") val isFollowingAd: Boolean,
)

fun AdRaw.toDomain() = Ad(
    id = this.id,
    posted = this.posted,
    locationName = this.locationName,
    name = this.name,
    price = this.price,
    currency = this.currency,
    priceFixed = this.priceFixed,
    photoThumbnailUrl = this.photoThumbnailUrl,
    favoriteCount = this.favoriteCount,
    isFollowingAd = this.isFollowingAd,
)
