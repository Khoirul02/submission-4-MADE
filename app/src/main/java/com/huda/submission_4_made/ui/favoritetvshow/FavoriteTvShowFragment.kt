package com.huda.submission_4_made.ui.favoritetvshow

import android.annotation.SuppressLint
import android.os.AsyncTask
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.huda.submission_4_made.R
import com.huda.submission_4_made.adapter.ListFavoriteTvShowAdapter
import com.huda.submission_4_made.db.DatabaseFavorite
import com.huda.submission_4_made.entity.DataFilm
import kotlinx.android.synthetic.main.favorite_tv_show_fragment.*

class FavoriteTvShowFragment : Fragment() {

    private lateinit var adapter: ListFavoriteTvShowAdapter
    private var databaseTvShow: DatabaseFavorite? = null
    private lateinit var mainViewModel: FavoriteTvShowViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.favorite_tv_show_fragment, container, false)
    }
    @SuppressLint("FragmentLiveDataObserve")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        databaseTvShow = activity?.let { DatabaseFavorite.getDatabase(it) }

        adapter = ListFavoriteTvShowAdapter()
        adapter.notifyDataSetChanged()

        rv_movies.layoutManager = LinearLayoutManager(activity)
        rv_movies.adapter = adapter

        mainViewModel = ViewModelProvider(
            this, ViewModelProvider.NewInstanceFactory()).get(FavoriteTvShowViewModel::class.java)
        showLoading(true)
        mainViewModel.getFilm().observe(this, Observer { tvShowList->
            if (tvShowList!!.isNotEmpty()) {
                adapter.setData(tvShowList)
                showLoading(false)
            } else{
                adapter.listTvShow = ArrayList()
                showSnackbarMessage("Tidak ada data saat ini")
                showLoading(false)
            }
        })
        getDataTvShow(this).execute()

    }
    private class getDataTvShow(var context: FavoriteTvShowFragment) : AsyncTask<Void, Void, List<DataFilm>>() {
        override fun doInBackground(vararg params: Void?): List<DataFilm> {
            return context.databaseTvShow!!.favoriteDao().getTvShow()
        }
        override fun onPostExecute(tvShowList: List<DataFilm>?) {
            if (tvShowList!!.isNotEmpty()) {
                context.mainViewModel.setFilm(tvShowList)
            } else {
                context.mainViewModel.setFilm(tvShowList)
            }
        }
    }
    private fun showLoading(state: Boolean) {
        if (state) {
            progressBar.visibility = View.VISIBLE
        } else {
            progressBar.visibility = View.GONE
        }
    }
    private fun showSnackbarMessage(message: String) {
        Snackbar.make(rv_movies, message, Snackbar.LENGTH_SHORT).show()
    }

}
