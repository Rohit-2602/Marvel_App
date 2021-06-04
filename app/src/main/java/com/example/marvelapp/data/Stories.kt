package com.example.marvelapp.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Stories(
    val available: String,
    val collectionURI: String,
    val items: List<StoriesItem>
) : Parcelable {

    @Parcelize
    data class StoriesItem(
        val name: String,
        val resourceURI: String,
        val type: String
    ): Parcelable

}