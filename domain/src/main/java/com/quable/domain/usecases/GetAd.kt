package com.quable.domain.usecases

import com.quable.domain.AdRepository
import com.quable.domain.models.Ad
import javax.inject.Inject

class GetAd @Inject constructor(
    private val adsRepository: AdRepository,
) {
    fun execute(id: Long): Ad? = adsRepository.getAdById(id)
}
