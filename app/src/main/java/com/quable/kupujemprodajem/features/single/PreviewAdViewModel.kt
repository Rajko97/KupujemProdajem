package com.quable.kupujemprodajem.features.single

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.quable.domain.usecases.GetAd
import com.quable.domain.usecases.GetAdDetails
import com.quable.domain.usecases.GetFollowingAd
import com.quable.domain.usecases.GetPreviousAd
import com.quable.kupujemprodajem.features.AdView
import com.quable.kupujemprodajem.features.toView
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PreviewAdViewModel @Inject constructor(
    private val getAd: GetAd,
    private val getAdDetails: GetAdDetails,
    private val getPreviousAd: GetPreviousAd,
    private val getFollowingAd: GetFollowingAd,
) : ViewModel() {
    private val _adBasicLive = MutableLiveData<AdView>(AdView())
    private val _adDetailsLive = MutableLiveData<AdDetailsView>(AdDetailsView())

    val adBasicLive: LiveData<AdView>
        get() = _adBasicLive
    val adDetailsLive: LiveData<AdDetailsView>
        get() = _adDetailsLive

    private val _isLoadingLive = MutableLiveData<Boolean>()
    val isLoadingLive: LiveData<Boolean>
        get() = _isLoadingLive

    private val _isErrorLive = MutableLiveData<Boolean>()
    val isErrorLive: LiveData<Boolean>
        get() = _isErrorLive

    init {
        setLoadingState()
    }

    private fun setLoadingState() {
        _isLoadingLive.value = true
        _isErrorLive.value = false
    }

    private fun setErrorState() {
        _isErrorLive.value = true
        _isLoadingLive.value = false
    }

    private fun setDataState() {
        _isErrorLive.value = false
        _isLoadingLive.value = false
    }

    fun initializeData(id: Long) {
        val adBasicData = getAd.execute(id)
        val adDetailsData = getAdDetails.execute(id.toString())
        if (adBasicData == null || adDetailsData == null) {
            setErrorState()
        } else {
            _adBasicLive.value = adBasicData.toView()
            _adDetailsLive.value = adDetailsData.toView()
            setDataState()
        }
    }

    fun onSwipeLeft() {
        _adBasicLive.value?.let {
            setLoadingState()
            getFollowingAd.execute(it.id)?.let { prevAd ->
                initializeData(prevAd.id)
            }
        }
    }

    fun onSwipeRight() {
        _adBasicLive.value?.let {
            setLoadingState()
            getPreviousAd.execute(it.id)?.let { nextAd ->
                initializeData(nextAd.id)
            }
        }
    }
}
