package com.example.movies.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.movies.domain.FilmItemModel
import com.example.movies.repository.MainRepository

class MainViewModel: ViewModel (){
    private val repository=MainRepository()

    fun loadUpcoming():LiveData<MutableList<FilmItemModel>>{
        return repository.loadUpcoming()
    }

    fun loadItems():LiveData<MutableList<FilmItemModel>>{
        return repository.loadItem()
    }
}