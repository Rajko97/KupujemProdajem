package com.quable.domain

import com.quable.domain.models.AdDetails

interface AdDetailsRepository {

    fun getAdsDetailsById(adId: String): AdDetails?
}
