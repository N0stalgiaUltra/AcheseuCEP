package com.n0stalgiaultra.view.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.n0stalgiaultra.data.remote.CepDto
import com.n0stalgiaultra.domain.CepRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(private val repository: CepRepository): ViewModel() {

    private val _cepList = MutableLiveData<List<CepDto>>()
    val cepList: LiveData<List<CepDto>> get() = _cepList

    suspend fun getCepList(){
        _cepList.value = repository.getRemoteData()
        Log.d("ViewModel", "${_cepList.value!!.size}")
    }
}