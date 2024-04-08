package com.quable.data

import com.quable.data.assets.AssetsDataSource
import com.quable.domain.AdDetailsRepository
import com.quable.domain.models.AdDetails
import javax.inject.Inject

class AdDetailsRepositoryImpl @Inject constructor(
    private val assetsDataSource: AssetsDataSource,
) : AdDetailsRepository {
    override fun getAdsDetailsById(adId: String): AdDetails? {
        return assetsDataSource.getAdDetailsList().find { it.adId == adId }
    }
}
