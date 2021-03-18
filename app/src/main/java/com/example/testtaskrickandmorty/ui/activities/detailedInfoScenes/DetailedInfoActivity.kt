package com.example.testtaskrickandmorty.ui.activities.detailedInfoScenes

import android.annotation.SuppressLint
import android.os.Bundle
import com.example.testtaskrickandmorty.R
import com.example.testtaskrickandmorty.data.model.AnswerResults
import com.example.testtaskrickandmorty.di.detailedModule.DetailedActivityModule
import com.example.testtaskrickandmorty.room.App
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detailed_info.*
import moxy.MvpAppCompatActivity
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import kotlin.properties.Delegates

class DetailedInfoActivity : MvpAppCompatActivity(), DetailedInfoView {

    @InjectPresenter
    lateinit var detailedInfoPresenter: DetailedInfoPresenter

    @ProvidePresenter
    fun provideLandingDetailedActivityPresenter(): DetailedInfoPresenter {
        return App.appComponent.inject(
            DetailedActivityModule()
        ).presenter
    }

    var id by Delegates.notNull<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detailed_info)
        val getModelAnswerResults = intent.getSerializableExtra("KEY") as AnswerResults
        id = getModelAnswerResults.id!!
        detailedInfoPresenter.getCharactersByID(id)

    }

    override fun showEpisodes(listEpisode: List<String>) {
        textViewDisplayListOfEpisode.text = listEpisode.joinToString(separator = "\n\n")
    }

    override fun showEpisode(episode: String) {
        textViewDisplayListOfEpisode.text = episode
    }

    @SuppressLint("SetTextI18n")
    override fun getOneCharacters(model: AnswerResults) {
        textViewName.text = model.name
        textViewSpecies.text = model.species
        textViewGender.text = model.gender
        textViewStatus.text = model.status
        if (model.type == "") {
            textViewDisplayType.text = "Type missing"
        } else {
            textViewDisplayType.text = model.type
        }
        val endLine = arrayListOf<String>()
        textViewDisplayCreatedCharacter.text =
            model.created.take(19).replace("T", "\n", true)
        model.episode!!.forEach { episode ->
            val endLineFilter = episode.filter { it.isDigit() }
            endLine.add(endLineFilter)
        }
        if (endLine.size == 1) {
            detailedInfoPresenter.getEpisode(endLine[0], id)
        } else {
            detailedInfoPresenter.getEpisodes(endLine.joinToString(separator = ","), id)
        }
        Picasso.get()
            .load(model.image)
            .fit()
            .into(imageViewCharacter)
    }
}
