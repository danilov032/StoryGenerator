package com.example.storygenerator.presentation.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.GridLayoutManager
import com.example.storygenerator.R
import com.example.storygenerator.di.AppModule
import com.example.storygenerator.di.DaggerAppComponent
import com.example.storygenerator.presentation.adapters.AdapterCategories
import com.example.storygenerator.presentation.contracts.MainListContractsView
import com.example.storygenerator.presentation.presenters.MainListPresenter
import com.example.storygenerator.presentation.utils.Categories
import kotlinx.android.synthetic.main.fragment_list_categories.*
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import javax.inject.Inject

class ListCategoriesFragment : MvpAppCompatFragment(), MainListContractsView {
    @Inject
    lateinit var presenterLazy: dagger.Lazy<MainListPresenter>

    @InjectPresenter
    lateinit var presenter: MainListPresenter

    @ProvidePresenter
    fun providePresenter(): MainListPresenter {
        DaggerAppComponent.builder()
            .appModule(AppModule(requireActivity().application))
            .build()
            .injectListCategoriesFragment(this)

        return presenterLazy.get()
    }

    private val customAdapter: AdapterCategories by lazy {
        AdapterCategories{ category -> presenter.onClickCategory(category) }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerCategories.apply {
            layoutManager = GridLayoutManager(requireContext(),2)
            adapter = customAdapter
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_list_categories, container, false)
    }

    override fun onStart() {
        presenter.startActivity()
        super.onStart()
    }

    override fun showContents(listCategory: List<Categories>) {
        customAdapter.updateItems(listCategory)
    }

    override fun openCurrentContent(category: Categories, statusGetData: Boolean) {
        parentFragmentManager
            .beginTransaction()
            .replace(R.id.container, ListContentsFragment.newInstance(category.getId(), statusGetData))
            .addToBackStack(null)
            .commit()
    }

    companion object {
        fun newInstance() =
            ListCategoriesFragment()
    }
}