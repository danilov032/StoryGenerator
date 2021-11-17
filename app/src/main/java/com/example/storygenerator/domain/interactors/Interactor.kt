package com.example.storygenerator.domain.interactors

import com.example.storygenerator.data.repositories.ContentRepository
import com.example.storygenerator.domain.modeles.Content
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject

class Interactor @Inject constructor(private val repository: ContentRepository){
    fun getContent(category: Int): Observable<Content> = repository.getContent(category).toObservable()
}