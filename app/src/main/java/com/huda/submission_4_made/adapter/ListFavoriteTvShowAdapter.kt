package com.huda.submission_4_made.adapter

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.huda.submission_4_made.R
import com.huda.submission_4_made.activity.DetailFavoriteMovie
import com.huda.submission_4_made.entity.DataFilm
import kotlinx.android.synthetic.main.item_data.view.*

class ListFavoriteTvShowAdapter : RecyclerView.Adapter<ListFavoriteTvShowAdapter.ListViewHolder>() {

    var listTvShow = ArrayList<DataFilm>()
    fun setData(tvShowList: List<DataFilm>) {
        this.listTvShow = tvShowList as ArrayList<DataFilm>
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ListViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.item_data, viewGroup, false)
        return ListViewHolder(view)
    }

    override fun getItemCount(): Int = listTvShow.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(listTvShow[position])
    }
    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(tvShow: DataFilm) {
            with(itemView) {
                Glide.with(itemView.context)
                    .load("https://image.tmdb.org/t/p/w185_and_h278_bestv2" + tvShow.photo)
                    .apply(RequestOptions().override(512, 512))
                    .into(img_item_photo)
                tv_item_name.text = tvShow.name
                tv_item_description.text = tvShow.description
                tv_item_date.text = tvShow.date
                itemView.setOnClickListener {
                    val intent = Intent(context, DetailFavoriteMovie::class.java)
                    intent.putExtra(DetailFavoriteMovie.EXTRA_FILM, tvShow)
                    ContextCompat.startActivity(context, intent, Bundle())
                }
            }
        }
    }
}