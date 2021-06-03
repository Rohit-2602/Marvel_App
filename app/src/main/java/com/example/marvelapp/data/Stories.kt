package com.example.marvelapp.data

data class Stories(
    val available: String,
    val collectionURI: String,
    val items: List<StoriesItem>
) {

    data class StoriesItem(
        val name: String,
        val resourceURI: String,
        val type: String
    )

}