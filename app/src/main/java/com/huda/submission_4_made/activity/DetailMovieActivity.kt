package com.huda.submission_4_made.activity

import android.annotation.SuppressLint
import android.os.AsyncTask
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.huda.submission_4_made.R
import com.huda.submission_4_made.db.DatabaseFavorite
import com.huda.submission_4_made.entity.DataFilm
import com.huda.submission_4_made.model.RootData
import kotlinx.android.synthetic.main.activity_detail.*

@Suppress("NAME_SHADOWING", "NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class DetailMovieActivity : AppCompatActivity(){

    private var database: DatabaseFavorite? = null
    companion object {
        const val EXTRA_FILM = "extra_person"
    }

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        database = DatabaseFavorite.getDatabase(this)

        val data = intent.getParcelableExtra(EXTRA_FILM) as? RootData
        val idFilm = data!!.id
        CheckFavorite(this, idFilm).execute()
        tv_item_name_detail.text = data.name
        tv_item_description_detail.text = data.description
        Glide.with(this)
            .load("https://image.tmdb.org/t/p/w185_and_h278_bestv2" + data.photo)
            .apply(RequestOptions().override(512, 512))
            .into(img_item_photo_detail)
        val percent = data.rate * 10
        tv_nilai_rate_detail.text = percent.toString() + "%"

        img_favorite.setOnClickListener {
            val data = DataFilm(idfilm = data.id, name = data.name, photo = data.photo, description = data.description, date = data.date , rate = data.rate, category = "movie")
            ActionClick(this, idFilm, data).execute()
        }
    }
    private class InsertTask(var context: DetailMovieActivity, var data: DataFilm) : AsyncTask<Void, Void, Boolean>() {
        override fun doInBackground(vararg params: Void?): Boolean {
            context.database!!.favoriteDao().insert(data)
            return true
        }
        override fun onPostExecute(bool: Boolean?) {
            if (bool!!) {
                Toast.makeText(context, "Added to Favorite", Toast.LENGTH_LONG).show()
                val idFilm = data.idfilm
                if (idFilm != null) {
                    CheckFavorite(context, idFilm).execute()
                }
            }
        }
    }
    private class CheckFavorite(var context: DetailMovieActivity, var idFilm: Int) : AsyncTask<Void, Void, List<DataFilm>>() {
        override fun doInBackground(vararg params: Void?): List<DataFilm> {
            return context.database!!.favoriteDao().getById(idFilm)
        }
        override fun onPostExecute(checkList : List<DataFilm>) {
            if (checkList.isNotEmpty()) {
                context.img_favorite.setImageResource(R.drawable.ic_favorite_black)
            }else{
                context.img_favorite.setImageResource(R.drawable.ic_favorite_border_black)
            }
        }
    }
    private class ActionClick(var context: DetailMovieActivity, var idFilm: Int, var data: DataFilm) : AsyncTask<Void, Void, List<DataFilm>>() {
        override fun doInBackground(vararg params: Void?): List<DataFilm> {
            return context.database!!.favoriteDao().getById(idFilm)
        }
        override fun onPostExecute(checkList : List<DataFilm>) {
            if (checkList.isNotEmpty()) {
                for (i in 0..checkList.size - 1) {
                    DeleteTask(context, checkList[i].id).execute()
                }
            }else{
                InsertTask(context, data).execute()
            }
        }
    }
    private class DeleteTask(var context: DetailMovieActivity, var id: Long) : AsyncTask<Void, Void, Boolean>() {
        override fun doInBackground(vararg params: Void?): Boolean {
            context.database!!.favoriteDao().deleteById(id)
            return true
        }
        override fun onPostExecute(bool: Boolean?) {
            if (bool!!) {
                Toast.makeText(context,"Delete for Favorite",Toast.LENGTH_LONG).show()
                CheckFavorite(context, id.toInt()).execute()
            }
        }
    }
}