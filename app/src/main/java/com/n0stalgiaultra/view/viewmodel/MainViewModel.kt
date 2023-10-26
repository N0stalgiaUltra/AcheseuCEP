package com.n0stalgiaultra.view.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.n0stalgiaultra.data.local.CepLocal
import com.n0stalgiaultra.data.remote.CepDto
import com.n0stalgiaultra.domain.CepRepository

class MainViewModel(private val repository: CepRepository): ViewModel() {

    private val _cepList = MutableLiveData<List<CepDto>>()
    val cepList: LiveData<List<CepDto>> get() = _cepList

    private val _localCepList = MutableLiveData<List<CepLocal>>()
    val localCepList: LiveData<List<CepLocal>> get() = _localCepList


    suspend fun getCepList(state: String, city: String, street: String){
        _cepList.value = repository.getRemoteCep(state, city, street)
        Log.d("ViewModel", "${_cepList.value!!.size}")
    }

    suspend fun getCepData(cep: String){
        _cepList.value = emptyList()
        _cepList.value = listOf(repository.getCepData(cep))
        Log.d("ViewModel", "${_cepList.value!!.size}")
    }

    suspend fun getLocalData(){
        _localCepList.value = repository.getAllFavourites()
        Log.d("ViewModel", "${_localCepList.value!!.size}")
    }

    suspend fun favoriteCep(item: CepDto){
        repository.insertLocalData(item)
    }

    fun removeFavouriteCep(){

    }
}