package com.example.marvelapp.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Comics(
    val available: String,
    val collectionURI: String,
    val items: List<ComicListItem>
) : Parcelable {

    @Parcelize
    data class ComicListItem(val name: String, val resourceURI: String): Parcelable

}