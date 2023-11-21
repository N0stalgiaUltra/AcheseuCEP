package com.n0stalgiaultra.view.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.n0stalgiaultra.data.local.CepLocal
import com.n0stalgiaultra.data.remote.CepDto
import com.n0stalgiaultra.domain.CepRepository
import com.n0stalgiaultra.domain.usecase.FavoriteCepUseCase
import com.n0stalgiaultra.domain.usecase.GetCepUseCase
import com.n0stalgiaultra.domain.usecase.GetDataFromCepUseCase
import com.n0stalgiaultra.domain.usecase.GetFavoriteDataUseCase
import com.n0stalgiaultra.domain.usecase.UnfavoriteCepUseCase
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel(
    private val favoriteCepUseCase: FavoriteCepUseCase,
    private val unfavoriteCepUseCase: UnfavoriteCepUseCase,
    private val getCepUseCase: GetCepUseCase,
    private val getDataFromCepUseCase: GetDataFromCepUseCase,
    private val getFavoriteDataUseCase: GetFavoriteDataUseCase): ViewModel(){

    private val _cepList = MutableLiveData<List<CepDto>>()
    val cepList: LiveData<List<CepDto>> get() = _cepList

    private val _localCepList = MutableLiveData<List<CepLocal>>()
    val localCepList: LiveData<List<CepLocal>> get() = _localCepList


    suspend fun getCepList(state: String, city: String, street: String){
        _cepList.value = getCepUseCase.invoke(state, city, street)
        Log.d("ViewModel", "${_cepList.value!!.size}")
    }

    suspend fun getDataFromCep(cep: String){
        _cepList.value = emptyList()
        _cepList.value = listOf(getDataFromCepUseCase.invoke(cep))
        Log.d("ViewModel", "${_cepList.value!!.size}")
    }

    @OptIn(DelicateCoroutinesApi::class)
    fun getFavoriteItems(){
        _localCepList.value = listOf()
        GlobalScope.launch {
            val localData = getFavoriteDataUseCase.invoke()
            withContext(Dispatchers.Main) {
                _localCepList.postValue(localData)
            }
        }

    }

    suspend fun favoriteItem(item: CepDto){
        favoriteCepUseCase.invoke(item)
    }
    suspend fun unFavoriteItem(item: Any) {
        unfavoriteCepUseCase.invoke(item)
    }


}