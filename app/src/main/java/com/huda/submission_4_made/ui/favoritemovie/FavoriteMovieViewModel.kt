package com.huda.submission_4_made.ui.favoritemovie

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.huda.submission_4_made.entity.DataFilm

class FavoriteMovieViewModel : ViewModel() {

    private val listDataFilm = MutableLiveData<List<DataFilm>>()
    fun setFilm(movieList: List<DataFilm>) {
        this.listDataFilm.postValue(movieList)
    }
    fun getFilm(): LiveData<List<DataFilm>> {
        return listDataFilm
    }
}