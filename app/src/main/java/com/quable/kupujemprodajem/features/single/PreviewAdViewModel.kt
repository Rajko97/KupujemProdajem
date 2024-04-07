package com.quable.kupujemprodajem.features.single

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.quable.domain.usecases.GetAdDetails
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PreviewAdViewModel @Inject constructor(
    private val getAdDetails: GetAdDetails,
) : ViewModel() {

    private val _adDetailsLive = MutableLiveData<AdDetailsView>(AdDetailsView())
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

    fun initializeData(id: String) {
        val data = getAdDetails.execute(id)
        if (data == null) {
            setErrorState()
        } else {
            _adDetailsLive.value = data.toView()
            setDataState()
        }
    }
}
