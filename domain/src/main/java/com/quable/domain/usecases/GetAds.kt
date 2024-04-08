package com.quable.domain.usecases

import com.quable.domain.AdRepository
import com.quable.domain.models.AdsResponse
import javax.inject.Inject

class GetAds @Inject constructor(
    private val adsRepository: AdRepository,
) {
    fun execute(page: Int): AdsResponse = adsRepository.getAdsPaged(page)
}
