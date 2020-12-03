package com.digitalhouse.desafiowebservice.ui

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.digitalhouse.desafiowebservice.entities.Hqs
import com.digitalhouse.desafiowebservice.service.Repository
import kotlinx.coroutines.launch

class MainViewModel(val repository: Repository): ViewModel() {
    val listaHqs = MutableLiveData<List<Hqs>>()


    fun getAllHqsModel(){
        try {
            viewModelScope.launch {
                listaHqs.value = repository.getAllComics(
                        8,
                        10,
                        "1",
                        "0a5637945d1555943781a5d1b85242df",
                        "02b8358994e987547bbd221ba79d53a4"
                ).data.results


            }
        }catch (e: Exception){
            Log.e("MainViewModel", e.toString())
        }
    }
}