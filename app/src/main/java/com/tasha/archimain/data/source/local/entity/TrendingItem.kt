package com.tasha.archimain.data.source.local.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Entity(tableName = "trending_item")
@Parcelize
data class TrendingItem(

    @PrimaryKey(autoGenerate = true)
    val localId: Long? = null,

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

@Entity(tableName = "trending_remote_key")
@Parcelize
data class TrendingRemoteKey(
    @PrimaryKey(autoGenerate = true) val localId: Long? = null,
    val repoId: Long,
    val prevKey: Int?,
    val nextKey: Int?
) : Parcelable {

}