package com.example.storygenerator.domain.interactors

import com.example.storygenerator.data.repositories.ContentRepository
import com.example.storygenerator.data.utils.mopToContentEntity
import com.example.storygenerator.domain.modeles.Content
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject

class Interactor @Inject constructor(private val repository: ContentRepository){
    fun getContent(category: Int): Observable<Content> = repository.getContent(category).toObservable()

    fun getContentsWithBD(): Single<List<Content>> = repository.getContentsWithBD()

    fun deleteAll(): Completable = repository.deleteAll()

    fun insertContents(contents: List<Content>): Completable =
        repository.insertContents(contents.map { itemContent->
            itemContent.mopToContentEntity()
        })
}