package com.digitalhouse.desafiowebservice.entities

import android.accounts.AuthenticatorDescription

data class Hqs(
    val id: Int,
    val title: String,
    val thumbnail: Thumbnail,
    val description: String,
    val pageCount: Int,
    val images: List<Image>,
    val dates: List<ComicDate>,
    val prices: List<ComicPrice>
)
