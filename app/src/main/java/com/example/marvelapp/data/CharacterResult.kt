package com.example.marvelapp.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CharacterResult(
    val id: String,
    val name: String,
    val description: String,
    val thumbnail: Thumbnail,
    val comics: Comics,
    val series: Series,
    val urls: List<Url>
) : Parcelable {

    @Parcelize
    data class Thumbnail(val path: String, val extension: String): Parcelable

    @Parcelize
    data class Url(val type: String, val url: String): Parcelable

    @Parcelize
    data class Comics(val available: String): Parcelable

    @Parcelize
    data class Series(val available: String): Parcelable

}