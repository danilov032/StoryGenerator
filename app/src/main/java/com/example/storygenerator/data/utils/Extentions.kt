package com.example.storygenerator.data.utils

import android.util.Log
import com.example.storygenerator.data.responses.ContentResponse
import com.example.storygenerator.domain.modeles.Content
import io.reactivex.Observable
import java.util.concurrent.TimeUnit

fun ContentResponse.toContentModel() =
    with(this){
        Content(content = content ?: "")
    }
