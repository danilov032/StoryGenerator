package com.example.storygenerator.presentation.views

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.storygenerator.R
import com.example.storygenerator.di.AppModule
import com.example.storygenerator.di.DaggerAppComponent
import com.example.storygenerator.domain.modeles.Content
import com.example.storygenerator.presentation.adapters.AdapterContent
import com.example.storygenerator.presentation.contracts.ListContentsContractsView
import com.example.storygenerator.presentation.presenters.ListContentsPresenter
import com.example.storygenerator.presentation.utils.Categories
import kotlinx.android.synthetic.main.activity_list_contents.*
import kotlinx.android.synthetic.main.main_list_categories.*
import moxy.MvpAppCompatActivity
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import javax.inject.Inject
import android.view.View

import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlinx.android.synthetic.main.dialog.*


class ListContentsActivity : MvpAppCompatActivity(), ListContentsContractsView  {
    @Inject
    lateinit var presenterLazy: dagger.Lazy<ListContentsPresenter>

    @InjectPresenter
    lateinit var presenter: ListContentsPresenter

    @ProvidePresenter
    fun providePresenter(): ListContentsPresenter {
        DaggerAppComponent.builder()
            .appModule(AppModule(application))
            .build()
            .injectListContentsActivity(this)

        return presenterLazy.get()
    }

    private val customAdapter: AdapterContent by lazy { AdapterContent () }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_contents)

        recyclerContent.apply {
            layoutManager = LinearLayoutManager(this@ListContentsActivity)
            adapter = customAdapter
        }

        val category = intent.getSerializableExtra("category") as Categories
        val statusGetData = intent.getSerializableExtra("statusGetData") as Boolean

        this.title = category.getNameCategory()

        presenter.onStartActivity(category.getId(), statusGetData)
    }

    override fun showContents(listContent: List<Content>) {
        customAdapter.updateItems(listContent)
    }

    override fun showDialog() {
        val dialog = BottomSheetDialog(this@ListContentsActivity)
        dialog.setContentView(R.layout.dialog)
        dialog.setCanceledOnTouchOutside(false)

        dialog.btn_close.setOnClickListener { dialog.dismiss() }
        dialog.btn_repeat.setOnClickListener { presenter.repeatData() }
        dialog.show()
    }

    override fun showProgressBar() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideProgressBar() {
        progressBar.visibility = View.GONE
    }

    override fun onBackPressed() {
        presenter.clickBack()
        super.onBackPressed()
    }

}