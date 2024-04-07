package com.quable.data

import android.content.res.AssetManager
import com.google.gson.Gson
import com.quable.data.assets.entities.AdRaw
import com.quable.data.assets.entities.AdsResponseRaw
import com.quable.data.assets.entities.toDomain
import com.quable.domain.AdRepository
import com.quable.domain.models.Ad
import com.quable.domain.models.AdsResponse
import org.json.JSONArray
import org.json.JSONObject
import java.io.IOException
import javax.inject.Inject

private const val ITEMS_PER_PAGE = 10

class AdRepositoryImpl @Inject constructor(
    private val assetManager: AssetManager,
) : AdRepository {

    private val data: JSONObject = JSONObject(loadAssetFile())
    private val allAdsList = data.getJSONArray("listaOglasa").extractAds().map { it.toDomain() }

    // val addDetailsList: JSONArray = data.getJSONArray("detaljiOglasa")

    override fun getAds(page: Int?): AdsResponse {
        return AdsResponse(
            pages = calculateNumberOfPages(allAdsList.size),
            page = page ?: 1,
            ads = getPage(allAdsList, (page ?: 1) - 1),
        )
    }

    private fun loadAssetFile(): String {
        return try {
            assetManager.open("adsData.json").use { inputStream ->
                inputStream.bufferedReader().use { it.readText() }
            }
        } catch (e: IOException) {
            "{}"
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
