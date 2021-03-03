package com.example.testtaskrickandmorty.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.testtaskrickandmorty.data.model.AnswerResults
import com.squareup.picasso.Picasso
import androidx.recyclerview.widget.RecyclerView
import com.example.testtaskrickandmorty.R
import kotlinx.android.synthetic.main.item_view.view.*

class Adapter(private var callback: (AnswerResults) -> Unit) :
    RecyclerView.Adapter<Adapter.MyViewHolder>() {
    private var data = listOf<AnswerResults>()
    fun setData(data: List<AnswerResults>) {
        this.data = data
        notifyDataSetChanged()
    }

    fun addData(listItems: List<AnswerResults>) {
        val sizePast = this.data.size
        this.data = this.data + listItems
        val sizeNew = this.data.size
        notifyItemRangeChanged(sizePast, sizeNew)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_view,
                parent,
                false
            ), callback
        )
    }

    override fun getItemCount(): Int = data.count()

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(data[position])
    }

    class MyViewHolder(itemView: View, private var callback: (AnswerResults) -> Unit) :
        RecyclerView.ViewHolder(itemView) {
        private var imageViewCharacter: ImageView = itemView.imageViewCharacter
        private var textViewNameCharacter: TextView = itemView.textViewNameCharacter
        fun bind(model: AnswerResults) {
            textViewNameCharacter.text = model.name
            Picasso.get()
                .load(model.image)
                .fit()
                .into(imageViewCharacter)
            itemView.setOnClickListener {
                callback.invoke(model)
            }
        }
    }
}