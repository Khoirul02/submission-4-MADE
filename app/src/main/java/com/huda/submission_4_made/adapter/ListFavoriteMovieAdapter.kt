package com.huda.submission_4_made.adapter

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.huda.submission_4_made.R
import com.huda.submission_4_made.activity.DetailFavoriteMovie
import com.huda.submission_4_made.entity.DataFilm
import kotlinx.android.synthetic.main.item_data.view.*

class ListFavoriteMovieAdapter : RecyclerView.Adapter<ListFavoriteMovieAdapter.ListViewHolder>() {

    var listMovie = ArrayList<DataFilm>()
    fun setData(movieList: List<DataFilm>) {
        this.listMovie = movieList as ArrayList<DataFilm>
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ListViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.item_data, viewGroup, false)
        return ListViewHolder(view)
    }

    override fun getItemCount(): Int = listMovie.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(listMovie[position])
    }

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(movies: DataFilm) {
            with(itemView) {
                Glide.with(itemView.context)
                    .load("https://image.tmdb.org/t/p/w185_and_h278_bestv2" + movies.photo)
                    .apply(RequestOptions().override(512, 512))
                    .into(img_item_photo)
                tv_item_name.text = movies.name
                tv_item_description.text = movies.description
                tv_item_date.text = movies.date
                cd_detail.setOnClickListener {
                    val intent = Intent(context, DetailFavoriteMovie::class.java)
                    intent.putExtra(DetailFavoriteMovie.EXTRA_FILM, movies)
                    startActivity(context, intent, Bundle())
                }
            }
        }
    }
}