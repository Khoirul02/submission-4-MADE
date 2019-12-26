package com.huda.submission_4_made.ui.movie

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.huda.submission_4_made.R
import com.huda.submission_4_made.activity.DetailMovieActivity
import com.huda.submission_4_made.adapter.ListMovieAdapter
import com.huda.submission_4_made.model.RootData
import kotlinx.android.synthetic.main.movie_fragment.*

class MovieFragment : Fragment() {

    private lateinit var adapter: ListMovieAdapter
    private lateinit var mainViewModel: MovieViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.movie_fragment, container, false)
    }

    @SuppressLint("FragmentLiveDataObserve")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = ListMovieAdapter()
        adapter.notifyDataSetChanged()

        val language = resources.getString(R.string.language_string)

        rv_movies.layoutManager = LinearLayoutManager(activity)
        rv_movies.adapter = adapter

        mainViewModel = ViewModelProvider(
            this, ViewModelProvider.NewInstanceFactory()).get(MovieViewModel::class.java)
        mainViewModel.setFilm(language)
        showLoading(true)
        mainViewModel.getFilm().observe(this, Observer { filmItems ->
            if (filmItems != null) {
                adapter.setData(filmItems)
                showLoading(false)
            }
        })
    }

    private fun showLoading(state: Boolean) {
        if (state) {
            progressBar.visibility = View.VISIBLE
        } else {
            progressBar.visibility = View.GONE
        }
    }
}