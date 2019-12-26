package com.huda.submission_4_made.ui.tvshow

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.huda.submission_4_made.R
import com.huda.submission_4_made.adapter.ListTvShowAdapter
import kotlinx.android.synthetic.main.tv_show_fragment.*

class TvShowFragment : Fragment() {

    private lateinit var adapter: ListTvShowAdapter
    private lateinit var mainViewModel: TvShowViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.tv_show_fragment, container, false)
    }

    @SuppressLint("FragmentLiveDataObserve")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = ListTvShowAdapter()
        adapter.notifyDataSetChanged()

        val language = resources.getString(R.string.language_string)

        rv_movies.layoutManager = LinearLayoutManager(activity)
        rv_movies.adapter = adapter

        mainViewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        ).get(TvShowViewModel::class.java)
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