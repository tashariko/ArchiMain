package com.tasha.archimain.data.source.remote.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ReTrendingItem(
    @SerializedName("id")
    val id: Long,

    @SerializedName("original_title")
    val originalTitle: String? = null,

    @SerializedName("poster_path")
    val posterPath: String? = null,

    @SerializedName("overview")
    val overview: String? = null,

    @SerializedName("media_type")
    val mediaType: String
) : Parcelable