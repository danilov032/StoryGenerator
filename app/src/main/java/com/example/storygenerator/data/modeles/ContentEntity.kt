package com.example.storygenerator.data.modeles

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class ContentEntity (
    @PrimaryKey(autoGenerate = true) var id: Int? = null,
    val id_category: Int,
    val content_str: String
    )