package com.huda.submission_4_made.ui.favoritetvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.huda.submission_4_made.entity.DataFilm

class FavoriteTvShowViewModel : ViewModel() {

    private val listDataFilm = MutableLiveData<List<DataFilm>>()
    fun setFilm(tvShowList: List<DataFilm>) {
        this.listDataFilm.postValue(tvShowList)
    }
    fun getFilm(): LiveData<List<DataFilm>> {
        return listDataFilm
    }
}
