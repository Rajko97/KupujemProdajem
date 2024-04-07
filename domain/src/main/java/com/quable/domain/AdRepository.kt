package com.quable.domain

import com.quable.domain.models.AdsResponse

interface AdRepository {

    fun getAds(page: Int? = 1): AdsResponse
}
