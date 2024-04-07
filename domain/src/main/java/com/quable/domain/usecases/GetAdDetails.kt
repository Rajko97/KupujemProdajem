package com.quable.domain.usecases

import com.quable.domain.AdRepository
import com.quable.domain.models.AdDetails
import javax.inject.Inject

class GetAdDetails @Inject constructor(
    private val adsRepository: AdRepository,
) {
    fun execute(adId: String): AdDetails? = adsRepository.getAdsDetails(adId)
}
