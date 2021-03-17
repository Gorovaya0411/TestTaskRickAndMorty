package com.example.testtaskrickandmorty.ui.activities.mainScenes

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Bundle
import android.widget.ProgressBar
import androidx.recyclerview.widget.GridLayoutManager
import com.example.testtaskrickandmorty.R
import com.example.testtaskrickandmorty.data.model.AnswerResults
import com.example.testtaskrickandmorty.di.mainModule.MainActivityModule
import com.example.testtaskrickandmorty.room.AnswerResultDao
import com.example.testtaskrickandmorty.room.App
import com.example.testtaskrickandmorty.room.AppDatabase
import com.example.testtaskrickandmorty.ui.Adapter
import com.example.testtaskrickandmorty.ui.PaginationScrollListener
import com.example.testtaskrickandmorty.ui.activities.detailedInfoScenes.DetailedInfoActivity
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Consumer
import kotlinx.android.synthetic.main.activity_main.*
import moxy.MvpAppCompatActivity
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter


class MainActivity : MvpAppCompatActivity(), MainView {

    @InjectPresenter
    lateinit var mainPresenter: MainPresenter

    @ProvidePresenter
    fun provideLandingActivityPresenter(): MainPresenter {
        return App.appComponent.inject(
            MainActivityModule()
        ).presenter
    }

    private val myAdapter =
        Adapter { openingNewActivity(it) }

    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        workWithAdapter()
        workWithToolbar()
        visibilityProgressBar(false)
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

    override fun visibilityProgressBar(isVisible: Boolean) {
        when (isVisible) {
            true -> progressBar.visibility = ProgressBar.VISIBLE
            false -> progressBar.visibility = ProgressBar.INVISIBLE
        }
    }

    override fun populateData(model: List<AnswerResults>) {
        myAdapter.setData(model)
    }

    override fun addData(model: List<AnswerResults>) {
        myAdapter.addData(model)
    }
}

