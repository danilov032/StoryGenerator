package com.example.storygenerator.data.repositories

import com.example.storygenerator.data.api.ApiService
import com.example.storygenerator.data.db.ContentDao
import com.example.storygenerator.data.modeles.ContentEntity
import com.example.storygenerator.data.utils.*
import com.example.storygenerator.domain.modeles.Content
import io.reactivex.Completable
import io.reactivex.Single

class ContentRepository(private val apiService: ApiService, private val contentDB: ContentDao) {
    fun getContent(category: Int): Single<Content> =
        apiService.getListContents(category).map { content ->
            content.mapToContentModel(category)
        }

    fun getContentsWithBD(): Single<List<Content>> = contentDB.getContents().
            map { list ->
                list.map { item->
                    item.mapToContent()
                }
            }

    fun deleteAll(): Completable = contentDB.deleteAll()

    fun insertContents(contents: List<ContentEntity>): Completable = contentDB.insertContents(contents)
}