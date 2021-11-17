package com.example.storygenerator.di

import com.example.storygenerator.presentation.views.ListContentsActivity
import com.example.storygenerator.presentation.views.MainListCategoriesActivity
import dagger.Component

@Component(modules = [AppModule::class])
interface AppComponent {

    fun injectMainListCategoriesActivity(activity: MainListCategoriesActivity)
    fun injectListContentsActivity(activity: ListContentsActivity)
}