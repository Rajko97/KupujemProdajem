package com.quable.domain.models

data class AdsResponse(
    val pages: Int,
    val page: Int,
    val ads: List<Ad>,
)
