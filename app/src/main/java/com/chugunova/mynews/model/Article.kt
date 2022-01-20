package com.chugunova.mynews.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "articles")
data class Article(
    @PrimaryKey val id: Long?,
    var typeOfQuery: String?, //can be "us", "apple", "sony" and more
//    @Ignore val source: Source,
    val author: String?,
    val title: String,
    val description: String?,
    val url: String,
    val urlToImage: String?,
    var publishedAt: String,
    val content: String?
) : Parcelable