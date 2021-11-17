package com.example.storygenerator.presentation.views

import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.storygenerator.R
import com.example.storygenerator.di.AppModule
import com.example.storygenerator.di.DaggerAppComponent
import com.example.storygenerator.domain.modeles.Content
import com.example.storygenerator.presentation.adapters.AdapterCategories
import com.example.storygenerator.presentation.contracts.MainListContractsView
import com.example.storygenerator.presentation.presenters.MainListPresenter
import com.example.storygenerator.presentation.utils.Categories
import kotlinx.android.synthetic.main.main_list_categories.*
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

    private val customAdapter: AdapterCategories by lazy {
        AdapterCategories{ category -> presenter.onClickCategory(category) }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_list_categories)

        recyclerCategories.apply {
            layoutManager = GridLayoutManager(applicationContext,2)
            adapter = customAdapter
        }
    }

    override fun showContents(listCategory: List<Categories>) {
        customAdapter.updateItems(listCategory)
    }

    override fun openCurrentContent(category: Categories) {
        val intent = Intent(this, ListContentsActivity::class.java)
        intent.putExtra("category",category)
        startActivity(intent)
    }
}