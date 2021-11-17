package com.example.storygenerator.di

import com.example.storygenerator.presentation.views.MainListCategoriesActivity
import dagger.Component

@Component(modules = [AppModule::class])
interface AppComponent {

    fun injectMainListCategoriesActivity(activity: MainListCategoriesActivity)
}