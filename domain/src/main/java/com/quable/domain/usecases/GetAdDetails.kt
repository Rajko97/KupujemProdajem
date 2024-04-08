package com.quable.domain.usecases

import com.quable.domain.AdDetailsRepository
import com.quable.domain.models.AdDetails
import javax.inject.Inject

class GetAdDetails @Inject constructor(
    private val adsRepository: AdDetailsRepository,
) {
    fun execute(adId: String): AdDetails? = adsRepository.getAdsDetailsById(adId)
}
