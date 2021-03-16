package com.example.testtaskrickandmorty.ui.activities.detailedInfoScenes

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.room.Room
import com.example.testtaskrickandmorty.R
import com.example.testtaskrickandmorty.data.model.AnswerResults
import com.example.testtaskrickandmorty.di.detailedModule.DetailedActivityModule
import com.example.testtaskrickandmorty.room.AnswerResultDao
import com.example.testtaskrickandmorty.room.App
import com.example.testtaskrickandmorty.room.AppDatabase
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detailed_info.*
import moxy.MvpAppCompatActivity
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter

class DetailedInfoActivity : MvpAppCompatActivity(), DetailedInfoView {

    @InjectPresenter
    lateinit var detailedInfoPresenter: DetailedInfoPresenter

    @ProvidePresenter
    fun provideLandingDetailedActivityPresenter(): DetailedInfoPresenter {
        return App.appComponent.inject(
            DetailedActivityModule()
        ).presenter
    }

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detailed_info)
        val getModelAnswerResults = intent.getStringExtra("KEY") as String
        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "database"
        ).build()
        val dao: AnswerResultDao = db.answerResultDao()
        val employee = dao.getById(getModelAnswerResults.toInt())

        textViewName.text = employee.name
        textViewSpecies.text = employee.species
        textViewGender.text = employee.gender
        textViewStatus.text = employee.status
        if (employee.type == "") {
            textViewDisplayType.text = "Type missing"
        } else {
            textViewDisplayType.text = employee.type
        }
        val endLine = arrayListOf<String>()
        textViewDisplayCreatedCharacter.text =
            employee.created.take(19).replace("T", "\n", true)
//        employee.episodes.forEach { episode ->
//            val endLineFilter = episode.filter { it.isDigit() }
//            endLine.add(endLineFilter)
//        }
        if (endLine.size == 1) {
            detailedInfoPresenter.getEpisode(endLine[0])
        } else {
            detailedInfoPresenter.getEpisodes(endLine.joinToString(separator = ","))
        }
        Picasso.get()
            .load(employee.image)
            .fit()
            .into(imageViewCharacter)
//        showEpisode(employee.episode)
//        showEpisodes(employee.episodes)

    }

     private fun showEpisodes(listEpisode: List<String>) {
        textViewDisplayListOfEpisode.text = listEpisode.joinToString(separator = "\n\n")
    }

     private fun showEpisode(episode: String) {
        textViewDisplayListOfEpisode.text = episode

    }
}
