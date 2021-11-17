package com.example.storygenerator.data.repositories

import com.example.storygenerator.data.api.ApiService
import com.example.storygenerator.data.utils.toContentModel
import com.example.storygenerator.domain.modeles.Content
import io.reactivex.Single

class ContentRepository(private val apiService: ApiService) {
    fun getContent(category: Int): Single<Content> =
        apiService.getListContents(category).map { content ->
            content.toContentModel()
        }
}