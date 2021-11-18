package com.example.storygenerator.presentation.views

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.recyclerview.widget.GridLayoutManager
import com.example.storygenerator.R
import com.example.storygenerator.di.AppModule
import com.example.storygenerator.di.DaggerAppComponent
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
            .appModule(AppModule(application))
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

    override fun onStart() {
        presenter.startActivity()
        super.onStart()
    }

    override fun showContents(listCategory: List<Categories>) {
        recyclerCategories.isVisible = true
        customAdapter.updateItems(listCategory)
    }

    override fun openCurrentContent(category: Categories, statusGetData: Boolean) {
        val intent = Intent(this, ListContentsActivity::class.java)
        intent.putExtra("category",category)
        intent.putExtra("statusGetData",statusGetData)
        startActivity(intent)
    }
}