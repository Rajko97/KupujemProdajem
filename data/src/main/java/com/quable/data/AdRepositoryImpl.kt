package com.quable.data

import android.content.res.AssetManager
import com.google.gson.Gson
import com.quable.data.assets.entities.AdsResponseRaw
import com.quable.data.assets.entities.toDomain
import com.quable.domain.AdRepository
import com.quable.domain.models.AdsResponse
import org.json.JSONArray
import org.json.JSONObject
import java.io.IOException
import javax.inject.Inject

class AdRepositoryImpl @Inject constructor(
    private val assetManager: AssetManager,
) : AdRepository {

    private val data: JSONObject = JSONObject(loadAssetFile())
    private val addsList: JSONArray = data.getJSONArray("listaOglasa")
    // val addDetailsList: JSONArray = data.getJSONArray("detaljiOglasa")

    override fun getAds(page: Int?): AdsResponse {
        return Gson().fromJson(
            addsList.getJSONObject(page?.minus(1) ?: 0).toString(),
            AdsResponseRaw::class.java,
        ).toDomain()
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
}
