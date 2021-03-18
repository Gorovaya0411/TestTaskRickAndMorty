package com.example.testtaskrickandmorty.ui.activities.mainScenes

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.widget.ProgressBar
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.GridLayoutManager
import com.example.testtaskrickandmorty.R
import com.example.testtaskrickandmorty.data.model.AnswerResults
import com.example.testtaskrickandmorty.di.mainModule.MainActivityModule
import com.example.testtaskrickandmorty.room.App
import com.example.testtaskrickandmorty.ui.Adapter
import com.example.testtaskrickandmorty.ui.PaginationScrollListener
import com.example.testtaskrickandmorty.ui.activities.detailedInfoScenes.DetailedInfoActivity
import kotlinx.android.synthetic.main.activity_main.*
import moxy.MvpAppCompatActivity
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import java.util.*


class MainActivity : MvpAppCompatActivity(), MainView {
    private lateinit var random: Random
    private lateinit var handler: Handler
    private lateinit var runnable: Runnable

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

    @RequiresApi(Build.VERSION_CODES.M)
    @SuppressLint("CheckResult", "ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        workWithAdapter()
        workWithToolbar()
        visibilityProgressBar(false)
        mainPresenter.swipeRefresh()

        random = Random()
        handler = Handler()

        swipeRefreshLayout.setOnRefreshListener {

            runnable = Runnable {
                lackInternet()
                mainPresenter.swipeRefresh()
                mainPresenter.getMoreItems()
                swipeRefreshLayout.isRefreshing = false
            }

            handler.postDelayed(
                runnable, 3000.toLong()
            )
        }

        swipeRefreshLayout.setColorSchemeResources(
            R.color.color,
            R.color.color1,
            R.color.color2
        )
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
            true -> {
                progressBar.visibility = ProgressBar.VISIBLE
            }
            false -> {
                progressBar.visibility = ProgressBar.INVISIBLE
            }
        }
    }

    override fun populateData(model: List<AnswerResults>) {
        myAdapter.setData(model)
    }

    override fun addData(model: List<AnswerResults>) {
        myAdapter.addData(model)
    }

    fun lackInternet(): Boolean {
        val connectionManager: ConnectivityManager =
            this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = connectionManager.activeNetworkInfo
        val isConnected: Boolean = activeNetwork?.isConnectedOrConnecting == true
        if (!isConnected) {
            Toast.makeText(
                this,
                "Подключение к интернету отключено",
                Toast.LENGTH_LONG
            )
                .show()
            mainPresenter.lackInternet()
        } else {
            Toast.makeText(
                this,
                "Подключение к интернету подключено",
                Toast.LENGTH_LONG
            ).show()
        }
        return isConnected
    }
}

