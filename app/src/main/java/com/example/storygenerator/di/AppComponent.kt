package com.example.storygenerator.di

import com.example.storygenerator.presentation.views.ListCategoriesFragment
import com.example.storygenerator.presentation.views.ListContentsFragment
import com.example.storygenerator.presentation.views.MainActivity
import dagger.Component

@Component(modules = [AppModule::class])
interface AppComponent {

    fun injectMainListCategoriesActivity(activity: MainActivity)
    fun injectListCategoriesFragment(fragment: ListCategoriesFragment)
    fun injectListContentsFragment(fragment: ListContentsFragment)
}