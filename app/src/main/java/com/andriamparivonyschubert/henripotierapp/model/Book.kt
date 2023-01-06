package com.andriamparivonyschubert.henripotierapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Book (
    @PrimaryKey val isbn: String,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "price") val price: String,
    @ColumnInfo(name = "cover") val cover: String,
    @ColumnInfo(name = "synopsis") val synopsis: List<String>
)