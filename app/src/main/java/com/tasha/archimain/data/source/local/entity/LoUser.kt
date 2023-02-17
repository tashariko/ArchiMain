package com.tasha.archimain.data.source.local.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.tasha.archimain.data.source.USER_LANGUAGE
import kotlinx.parcelize.Parcelize


@Entity(tableName = "user")
@Parcelize
data class LoUser(
    @PrimaryKey(autoGenerate = true)
    val id: Long? = null,

    @ColumnInfo(name = "name")
    @SerializedName("name")
    val name: String,

    @ColumnInfo(name = "language")
    @SerializedName("language")
    val language: USER_LANGUAGE,

    @ColumnInfo(name = "registeredAt")
    @SerializedName("registeredAt")
    val registeredAt: String
) : Parcelable {

    override fun toString() = name
}

