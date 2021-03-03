package com.example.testtaskrickandmorty.ui.activities.detailedInfoScenes

import android.os.Bundle
import com.example.testtaskrickandmorty.MyApplication
import com.example.testtaskrickandmorty.R
import com.example.testtaskrickandmorty.data.model.AnswerResults
import com.example.testtaskrickandmorty.ui.activities.mainScenes.MainPresenter
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detailed_info.*
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter
import moxy.presenter.InjectPresenter

class DetailedInfoActivity : MvpAppCompatActivity(), DetailedInfoView {

    @InjectPresenter
    lateinit var detailedInfoPresenter: DetailedInfoPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        MyApplication.appComponent.injectDetailedActivityModule(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detailed_info)
        val getModelAnswerResults = intent.getSerializableExtra("KEY") as AnswerResults
        textViewName.setOnClickListener { textViewName.maxLines = Integer.MAX_VALUE }
        textViewName.text = getModelAnswerResults.name
        textViewSpecies.text = getModelAnswerResults.species
        textViewGender.text = getModelAnswerResults.gender
        textViewStatus.text = getModelAnswerResults.status
        if (getModelAnswerResults.type == "") {
            textViewDisplayType.text = "-"
        } else {
            textViewDisplayType.text = getModelAnswerResults.type
        }
        val endLine = arrayListOf<String>()
        textViewDisplayCreatedCharacter.text =
            getModelAnswerResults.created.take(19).replace("T", "\n", true)
        getModelAnswerResults.episode.forEach { episode ->
            val endLineFilter = episode.filter { it.isDigit() }
            endLine.add(endLineFilter)
        }
        if (endLine.size == 1) {
            detailedInfoPresenter.getEpisode(endLine[0])
        } else {
            detailedInfoPresenter.getEpisodes(endLine.joinToString(separator = ","))
        }
        Picasso.get()
            .load(getModelAnswerResults.image)
            .fit()
            .into(imageViewCharacter)

    }

    override fun showEpisodes(listEpisode: List<String>) {
        textViewDisplayListOfEpisode.text = listEpisode.joinToString(separator = "\n")
    }

    override fun showEpisode(episode: String) {
        textViewDisplayListOfEpisode.text = episode

    }
}
