package com.example.storygenerator.presentation.views

import android.os.Bundle
import com.example.storygenerator.R
import com.example.storygenerator.di.AppModule
import com.example.storygenerator.di.DaggerAppComponent
import com.example.storygenerator.domain.modeles.Content
import com.example.storygenerator.presentation.contracts.MainListContractsView
import com.example.storygenerator.presentation.presenters.MainListPresenter
import moxy.MvpAppCompatActivity
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import javax.inject.Inject

class MainListCategoriesActivity : MvpAppCompatActivity(), MainListContractsView {
    @Inject
    lateinit var presenterLazy: dagger.Lazy<MainListPresenter>

    @InjectPresenter
    lateinit var presenter: MainListPresenter

    @ProvidePresenter
    fun providePresenter(): MainListPresenter {
        DaggerAppComponent.builder()
            .appModule(AppModule())
            .build()
            .injectMainListCategoriesActivity(this)

        return presenterLazy.get()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_list_categories)
    }

    override fun getContents() {

    }
}