package com.tasha.archimain.data.source.local.entity

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Genre(
    val id: Long,
    val name: String
) : Parcelable

@Parcelize
data class SpokenLanguage(
    @SerializedName("english_name")
    val englishName: String,

    @SerializedName("iso_639_1")
    val iso639_1: String,

    val name: String
) : Parcelable
