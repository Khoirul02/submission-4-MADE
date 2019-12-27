package com.huda.submission_4_made.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.startActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.huda.submission_4_made.R
import com.huda.submission_4_made.db.DatabaseFavorite
import com.huda.submission_4_made.entity.DataFilm
import kotlinx.android.synthetic.main.activity_detail_favorite.*

@SuppressLint("Registered")
class DetailFavoriteTvShow : AppCompatActivity() {

    private var database: DatabaseFavorite? = null
    companion object {
        const val EXTRA_FILM = "extra_person"
    }

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_favorite)

        database = DatabaseFavorite.getDatabase(this)

        val data = intent.getParcelableExtra(EXTRA_FILM) as? DataFilm
        tv_item_name_detail.text = data?.name
        tv_item_description_detail.text = data?.description
        Glide.with(this)
            .load("https://image.tmdb.org/t/p/w185_and_h278_bestv2" + data?.photo)
            .apply(RequestOptions().override(512, 512))
            .into(img_item_photo_detail)
        val percent = data?.rate!! * 10
        tv_nilai_rate_detail.text = percent.toString() + "%"

        img_favorite.setOnClickListener {
            val idFilm = data.id
            DeleteTask(this,idFilm).execute()
        }
    }
    private class DeleteTask(var context: DetailFavoriteTvShow, var id: Long) : AsyncTask<Void, Void, Boolean>() {
        override fun doInBackground(vararg params: Void?): Boolean {
            context.database!!.favoriteDao().deleteById(id)
            return true
        }
        override fun onPostExecute(bool: Boolean?) {
            if (bool!!) {
                Toast.makeText(context, "Delete for Favorite", Toast.LENGTH_LONG).show()
                val intent = Intent(context, MainActivity::class.java)
                startActivity(context,intent, Bundle())
            }
        }
    }
}