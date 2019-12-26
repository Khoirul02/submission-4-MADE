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
import com.huda.submission_4_made.activity.DetailTvShowActivity
import com.huda.submission_4_made.model.RootData
import kotlinx.android.synthetic.main.item_data.view.*

class ListTvShowAdapter : RecyclerView.Adapter<ListTvShowAdapter.ListViewHolder>() {


    private val mData = ArrayList<RootData>()
    fun setData(items: ArrayList<RootData>) {
        mData.clear()
        mData.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ListViewHolder {
        val view =
            LayoutInflater.from(viewGroup.context).inflate(R.layout.item_data, viewGroup, false)
        return ListViewHolder(view)
    }

    override fun getItemCount(): Int = mData.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(mData[position])
    }
    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(tvshow: RootData) {
            with(itemView) {
                Glide.with(itemView.context)
                    .load("https://image.tmdb.org/t/p/w185_and_h278_bestv2" + tvshow.photo)
                    .apply(RequestOptions().override(512, 512))
                    .into(img_item_photo)
                tv_item_name.text = tvshow.name
                tv_item_description.text = tvshow.description
                tv_item_date.text = tvshow.date
                cd_detail.setOnClickListener {
                    val intent = Intent(context, DetailTvShowActivity::class.java)
                    intent.putExtra(DetailTvShowActivity.EXTRA_FILM, tvshow)
                    startActivity(context, intent, Bundle())
                }
            }
        }
    }
}