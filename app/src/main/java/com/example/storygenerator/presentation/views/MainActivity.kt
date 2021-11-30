package com.example.storygenerator.presentation.views

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import com.example.storygenerator.R
import com.example.storygenerator.di.AppModule
import com.example.storygenerator.di.DaggerAppComponent
import com.example.storygenerator.presentation.OnBackPressed

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_list_categories)

        DaggerAppComponent.builder()
            .appModule(AppModule(application))
            .build()
            .injectMainListCategoriesActivity(this)


            supportFragmentManager
                .beginTransaction()
                .add(R.id.container, ListCategoriesFragment.newInstance())
                .addToBackStack(null)
                .commit()

    }

    override fun onBackPressed() {
        val fm: FragmentManager = supportFragmentManager
        var backPressedListener: OnBackPressed? = null
        for (fragment in fm.fragments) {
            if (fragment is OnBackPressed) {
                backPressedListener = fragment
                break
            }
        }
        if (backPressedListener != null) {
            backPressedListener.onBackPressed()
            super.onBackPressed()
        }
    }
}
