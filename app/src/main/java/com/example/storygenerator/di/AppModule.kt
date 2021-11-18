package com.example.storygenerator.di

import android.app.Application
import com.example.storygenerator.data.api.ApiService
import com.example.storygenerator.data.db.dbAbstract
import com.example.storygenerator.data.repositories.ContentRepository
import dagger.Module
import dagger.Provides

@Module
class AppModule(application: Application) {
    @Provides
    fun provideContentRepository(): ContentRepository {
        return ContentRepository(apiService, dataBase)
    }

    @get:Provides
    val apiService = ApiService.create()

    @get:Provides
    val dataBase = dbAbstract.getDatabase(application).contentsDao()
}