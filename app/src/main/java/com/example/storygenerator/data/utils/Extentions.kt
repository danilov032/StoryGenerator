package com.example.storygenerator.data.utils

import android.content.res.Resources
import android.util.Log
import com.example.storygenerator.data.modeles.ContentEntity
import com.example.storygenerator.data.responses.ContentResponse
import com.example.storygenerator.domain.modeles.Content
import io.reactivex.Observable
import java.util.concurrent.TimeUnit

fun ContentResponse.mapToContentModel(id: Int): Content =
    with(this) {
        Content(id, content = content ?: "")
    }

val Int.dpToPx: Int get() = (this * Resources.getSystem().displayMetrics.density).toInt()

fun ContentEntity.mapToContent():Content =
    with(this) {
        Content(
            id_category, content_str
        )
    }

fun Content.mopToContentEntity() : ContentEntity=
    with(this) {
        ContentEntity(
            id_category = idCategory,
            content_str = content
        )
    }