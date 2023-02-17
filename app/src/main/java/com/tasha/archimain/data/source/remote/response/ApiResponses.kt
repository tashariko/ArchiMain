package com.tasha.archimain.data.source.remote.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Images(
    @SerializedName("base_url") val base_url: String,
    @SerializedName("secure_base_url") val secure_base_url: String,
    @SerializedName("backdrop_sizes") val backdrop_sizes: List<String>,
    @SerializedName("logo_sizes") val logo_sizes: List<String>,
    @SerializedName("poster_sizes") val poster_sizes: List<String>,
    @SerializedName("profile_sizes") val profile_sizes: List<String>,
    @SerializedName("still_sizes") val still_sizes: List<String>
) : Parcelable

@Parcelize
data class ConfigurationResponse(

    @SerializedName("images") val images: Images,
    @SerializedName("change_keys") val change_change_keyss: List<String>
) : Parcelable

@Parcelize
data class TrendingItemResponse(

    @SerializedName("total_pages") val totalPages: Long,
    @SerializedName("total_results") val totalResults: Long,
    @SerializedName("page") val page: Long,
    @SerializedName("results") val results: ArrayList<ReTrendingItem>
) : Parcelable
