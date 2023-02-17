package com.tasha.archimain.data.source.remote.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ReMovie(

    val adult: Boolean,
    @SerializedName("backdrop_path")
    val backdropPath: String,

    @SerializedName("budget")
    val budget: Long,

    @SerializedName("genres")
    val genres: ArrayList<Genre>? = null,

    val homepage: String,
    val id: Long,

    @SerializedName("imdb_id")
    val imdbID: String,

    @SerializedName("original_language")
    val originalLanguage: String,

    @SerializedName("original_title")
    val originalTitle: String,

    val overview: String,
    val popularity: Double,

    @SerializedName("poster_path")
    val posterPath: String,

    @SerializedName("production_companies")
    val productionCompanies: ArrayList<ProductionCompany>? = null,

    @SerializedName("release_date")
    val releaseDate: String,

    val revenue: Long,
    val runtime: Long,

    @SerializedName("spoken_languages")
    val spokenLanguages: ArrayList<SpokenLanguage>? = null,

    val status: String,
    val tagline: String,
    val title: String,
    val video: Boolean,

    @SerializedName("vote_average")
    val voteAverage: Double,

    @SerializedName("vote_count")
    val voteCount: Long
) : Parcelable

@Parcelize
data class ProductionCompany(
    val id: Long,

    @SerializedName("logo_path")
    val logoPath: String,

    val name: String,

    @SerializedName("origin_country")
    val originCountry: String
) : Parcelable
