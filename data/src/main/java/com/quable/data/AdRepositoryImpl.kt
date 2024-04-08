package com.quable.data

import com.quable.data.assets.AssetsDataSource
import com.quable.domain.AdRepository
import com.quable.domain.models.Ad
import com.quable.domain.models.AdsResponse
import javax.inject.Inject

class AdRepositoryImpl @Inject constructor(
    private val assetsDataSource: AssetsDataSource,
) : AdRepository {

    override fun getAdsPaged(page: Int?): AdsResponse {
        return assetsDataSource.getAdsListPaged(page)
    }

    override fun getAdById(adId: Long): Ad? {
        return assetsDataSource.getAdsList().find { it.id == adId }
    }

    override fun getPreviousAdById(adId: Long): Ad? {
        val index = findIndexFromId(adId)
        return index.takeIf { it > 0 }?.let { assetsDataSource.getAdsList()[it - 1] }
    }

    override fun getFollowingAdById(adId: Long): Ad? {
        val index = findIndexFromId(adId)
        return index.takeIf { it < assetsDataSource.getAdsList().size - 1 }
            ?.let { assetsDataSource.getAdsList()[it + 1] }
    }

    private fun findIndexFromId(adId: Long): Int {
        val element = getAdById(adId)
        return assetsDataSource.getAdsList().indexOf(element)
    }
}
