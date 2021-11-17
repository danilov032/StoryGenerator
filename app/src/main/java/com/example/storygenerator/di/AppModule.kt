package com.example.storygenerator.di

import com.example.storygenerator.data.api.ApiService
import com.example.storygenerator.data.repositories.ContentRepository
import dagger.Module
import dagger.Provides

@Module
class AppModule {
    @Provides
    fun provideContentRepository(): ContentRepository {
        return ContentRepository(apiService)
    }

    @get:Provides
    val apiService = ApiService.create()
}