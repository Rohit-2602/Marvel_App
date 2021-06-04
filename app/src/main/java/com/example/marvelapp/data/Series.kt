package com.example.marvelapp.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Series(
    val available: String,
    val collectionURI: String,
    val items: List<SeriesItem>
) : Parcelable {

    @Parcelize
    data class SeriesItem(val name: String, val resourceURI: String): Parcelable

}