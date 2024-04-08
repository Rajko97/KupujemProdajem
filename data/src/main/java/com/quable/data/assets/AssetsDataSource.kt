package com.quable.data.assets

import android.content.res.AssetManager
import com.google.gson.Gson
import com.quable.data.assets.entities.AdDetailsRaw
import com.quable.data.assets.entities.AdRaw
import com.quable.data.assets.entities.AdsResponseRaw
import com.quable.data.assets.entities.toDomain
import com.quable.domain.models.Ad
import com.quable.domain.models.AdDetails
import com.quable.domain.models.AdsResponse
import org.json.JSONArray
import org.json.JSONObject
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

private const val ITEMS_PER_PAGE = 10

@Singleton
class AssetsDataSource @Inject constructor(
    private val assetManager: AssetManager,
) {
    private val allAdsList: List<Ad>
    private val adDetailsList: List<AdDetails>

    init {
        val data = JSONObject(loadAssetFile())
        allAdsList = data.getJSONArray("listaOglasa").extractAds().map { it.toDomain() }
        adDetailsList = data.getJSONArray("detaljiOglasa").extractDetailsAds().map { it.toDomain() }
    }

    fun getAdsList() = allAdsList

    fun getAdsListPaged(page: Int?): AdsResponse {
        return AdsResponse(
            pages = calculateNumberOfPages(allAdsList.size),
            page = page ?: 1,
            ads = getPage(allAdsList, (page ?: 1) - 1),
        )
    }

    fun getAdDetailsList() = adDetailsList

    private fun loadAssetFile(): String {
        return try {
            assetManager.open("adsData.json").use { inputStream ->
                inputStream.bufferedReader().use { it.readText() }
            }
        } catch (e: IOException) {
            "{}"
        }
    }

    private fun JSONArray.extractAds() =
        mutableListOf<AdRaw>().apply {
            for (i in 0..3) {
                addAll(
                    Gson().fromJson(
                        this@extractAds.getJSONObject(i).toString(),
                        AdsResponseRaw::class.java,
                    ).ads,
                )
            }
        }

    private fun JSONArray.extractDetailsAds() =
        mutableListOf<AdDetailsRaw>().apply {
            for (i in 0..<this@extractDetailsAds.length()) {
                add(
                    Gson().fromJson(
                        this@extractDetailsAds.getJSONObject(i).toString(),
                        AdDetailsRaw::class.java,
                    ),
                )
            }
        }

    private fun calculateNumberOfPages(totalItemCount: Int): Int {
        return if (totalItemCount % ITEMS_PER_PAGE == 0) {
            totalItemCount / ITEMS_PER_PAGE
        } else {
            (totalItemCount / ITEMS_PER_PAGE) + 1
        }
    }

    private fun getPage(array: List<Ad>, pageIndex: Int): List<Ad> {
        val startIndex = pageIndex * ITEMS_PER_PAGE
        val endIndex = minOf((pageIndex + 1) * ITEMS_PER_PAGE, array.size)
        return array.subList(startIndex, endIndex)
    }
}
