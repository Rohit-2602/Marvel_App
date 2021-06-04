package com.example.marvelapp.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Data(val results: List<MarvelHero>): Parcelable