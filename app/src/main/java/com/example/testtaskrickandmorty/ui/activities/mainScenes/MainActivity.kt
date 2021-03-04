package com.example.testtaskrickandmorty.ui.activities.mainScenes

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.testtaskrickandmorty.MyApplication
import com.example.testtaskrickandmorty.R
import com.example.testtaskrickandmorty.data.model.AnswerResults
import com.example.testtaskrickandmorty.di.mainModule.MainActivityModule
import com.example.testtaskrickandmorty.ui.Adapter
import com.example.testtaskrickandmorty.ui.PaginationScrollListener
import com.example.testtaskrickandmorty.ui.activities.detailedInfoScenes.DetailedInfoActivity
import kotlinx.android.synthetic.main.activity_main.*
import moxy.MvpAppCompatActivity
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter

class MainActivity : MvpAppCompatActivity(), MainView {

    @InjectPresenter
    lateinit var mainPresenter: MainPresenter

    @ProvidePresenter
    fun provideLandingActivityPresenter(): MainPresenter {
        return MyApplication.appComponent.inject(
           MainActivityModule()
        ).presenter
    }

    private val myAdapter =
        Adapter { openingNewActivity(it) }

    override fun onCreate(savedInstanceState: Bundle?) {
//        MyApplication.appComponent.injectMainActivityModule(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        workWithAdapter()
        workWithToolbar()
        visibilityProgressBar(false)
        showInternet()
        mainPresenter.swipeRefresh()

        swipeRefreshLayout.setColorSchemeResources(
            android.R.color.holo_green_dark,
            android.R.color.holo_green_light,
            android.R.color.holo_green_dark,
            android.R.color.holo_green_light
        )

        swipeRefreshLayout.setOnRefreshListener {
            mainPresenter.swipeRefresh()
        }

        recyclerView.addOnScrollListener(
            PaginationScrollListener(
                { mainPresenter.getMoreItems() },
                20
            )
        )
    }

    private fun workWithToolbar() {
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(false)
        supportActionBar!!.title = ""
    }

    private fun openingNewActivity(model: AnswerResults) {
        val intent = Intent(this, DetailedInfoActivity::class.java)
        intent.putExtra("KEY", model)
        startActivity(intent)
    }

    private fun workWithAdapter() {
        recyclerView.layoutManager = GridLayoutManager(this, 2)
        recyclerView.adapter = myAdapter
    }

    override fun populateData(model: List<AnswerResults>) {
        myAdapter.setData(model)
    }

    override fun addData(model: List<AnswerResults>) {
        myAdapter.addData(model)
    }

    override fun visibilityProgressBar(isVisible: Boolean) {
        when (isVisible) {
            true -> progressBar.visibility = ProgressBar.VISIBLE
            false -> progressBar.visibility = ProgressBar.INVISIBLE
        }
    }

    override fun showNoInternet() {
        recyclerView.visibility = RecyclerView.INVISIBLE
        imageViewNoInternet.visibility = ImageView.VISIBLE
        textViewNoInternet.visibility = TextView.VISIBLE
        cardViewNoInternet.visibility = CardView.VISIBLE
        swipeRefreshLayout.isRefreshing = false
    }

    override fun showInternet() {
        recyclerView.visibility = RecyclerView.VISIBLE
        textViewNoInternet.visibility = TextView.INVISIBLE
        imageViewNoInternet.visibility = ImageView.INVISIBLE
        cardViewNoInternet.visibility = CardView.INVISIBLE
        swipeRefreshLayout.isRefreshing = false
    }
}