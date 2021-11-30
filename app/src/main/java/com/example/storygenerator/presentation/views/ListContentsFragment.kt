package com.example.storygenerator.presentation.views

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.storygenerator.R
import com.example.storygenerator.di.AppModule
import com.example.storygenerator.di.DaggerAppComponent
import com.example.storygenerator.domain.modeles.Content
import com.example.storygenerator.presentation.OnBackPressed
import com.example.storygenerator.presentation.adapters.AdapterContent
import com.example.storygenerator.presentation.contracts.ListContentsContractsView
import com.example.storygenerator.presentation.presenters.ListContentsPresenter
import com.example.storygenerator.presentation.utils.Categories
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlinx.android.synthetic.main.dialog.*
import kotlinx.android.synthetic.main.fragment_list_contents.*
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import javax.inject.Inject

private const val CATEGORY = "categoryId"
private const val STATUS = "statusGetData"

class ListContentsFragment : MvpAppCompatFragment(), ListContentsContractsView, OnBackPressed {
    @Inject
    lateinit var presenterLazy: dagger.Lazy<ListContentsPresenter>

    @InjectPresenter
    lateinit var presenter: ListContentsPresenter

    @ProvidePresenter
    fun providePresenter(): ListContentsPresenter {
        DaggerAppComponent.builder()
            .appModule(AppModule(requireActivity().application))
            .build()
            .injectListContentsFragment(this)

        return presenterLazy.get()
    }

    private val customAdapter: AdapterContent by lazy { AdapterContent() }

    private var category: Int = 1
    private var status: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            category = it.getInt(CATEGORY)
            status = it.getBoolean(STATUS)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_list_contents, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val layoutManagerForAdapter = LinearLayoutManager(requireContext())
        recyclerContent.apply {
            layoutManager = layoutManagerForAdapter
            adapter = customAdapter
        }

        requireActivity().title = Categories.getClassEnum(category).getNameCategory()

        presenter.onStartActivity(category, status)

        recyclerContent.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                val lastVisibleItemPosition: Int =
                    layoutManagerForAdapter.findLastVisibleItemPosition()
                if (lastVisibleItemPosition == customAdapter.itemCount - 1) {
                    Log.d("AAA", "Обновить")
                    presenter.updateData()

                }
            }
        })


    }

    override fun showContents(listContent: List<Content>) {
        customAdapter.updateItems(listContent)
    }

    override fun showDialog() {
        val dialog = BottomSheetDialog(requireContext())
        dialog.setContentView(R.layout.dialog)

        dialog.btn_repeat.setOnClickListener {
            presenter.repeatData()
            dialog.dismiss()
        }
        dialog.show()
    }

    override fun showProgressBar() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideProgressBar() {
        progressBar.visibility = View.GONE
    }

    override fun addDataRecycler(listContent: List<Content>) {
        customAdapter.addItems(listContent)
    }

    companion object {
        fun newInstance(categoryId: Int, status: Boolean) =
            ListContentsFragment().apply {
                arguments = Bundle().apply {
                    putInt(CATEGORY, categoryId)
                    putBoolean(STATUS, status)
                }
            }
    }

    override fun onBackPressed(){
        presenter.clickBack()
    }
}