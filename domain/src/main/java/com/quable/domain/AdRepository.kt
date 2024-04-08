package com.quable.domain

import com.quable.domain.models.Ad
import com.quable.domain.models.AdsResponse

interface AdRepository {

    fun getAdsPaged(page: Int? = 1): AdsResponse

    fun getAdById(adId: Long): Ad?

    fun getPreviousAdById(adId: Long): Ad?

    fun getFollowingAdById(adId: Long): Ad?
}
