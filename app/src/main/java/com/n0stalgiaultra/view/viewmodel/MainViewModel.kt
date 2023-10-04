package com.n0stalgiaultra.view.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.n0stalgiaultra.data.remote.CepDto
import com.n0stalgiaultra.domain.CepRepository

class MainViewModel(private val repository: CepRepository): ViewModel() {

    private val _cepList = MutableLiveData<List<CepDto>>()
    val cepList: LiveData<List<CepDto>> get() = _cepList

    suspend fun getCepList(state: String, city: String, street: String){
        _cepList.value = repository.getRemoteCep(state, city, street)
        Log.d("ViewModel", "${_cepList.value!!.size}")
    }

    suspend fun getCepData(){
        /*TODO: Recuperar dados de um CEP*/
    }

    fun favoriteCep(){
        /*TODO: Salvar os endereços salvos no banco local*/
    }
}