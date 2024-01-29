package com.n0stalgiaultra.view.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.n0stalgiaultra.domain.model.CepDto
import com.n0stalgiaultra.database.entity.CepLocal
import com.n0stalgiaultra.domain.mapper.Cep
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

    private val _cepList = MutableLiveData<List<Cep>>()
    val cepList: LiveData<List<Cep>> get() = _cepList

    private val _localCepList = MutableLiveData<List<Cep>>()
    val localCepList: LiveData<List<Cep>> get() = _localCepList



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
        //Não pode ser viewModelScope
        GlobalScope.launch {
            val localData = getFavoriteDataUseCase.invoke()
            withContext(Dispatchers.Main) {
                _localCepList.postValue(localData)
            }
        }

    }

    suspend fun favoriteItem(item: Cep){
        favoriteCepUseCase.invoke(item)
    }
    suspend fun unFavoriteItem(item: Cep) {
        unfavoriteCepUseCase.invoke(item)
    }


}