package com.example.storygenerator.data.responses

import com.google.gson.annotations.SerializedName

class ContentResponse (
    @SerializedName("content")
    val content: String?
    )