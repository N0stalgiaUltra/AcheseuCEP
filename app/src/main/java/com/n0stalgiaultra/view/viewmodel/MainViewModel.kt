package com.n0stalgiaultra.view.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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

    private val _remoteCepList = MutableLiveData<List<Cep>>()
    val remoteCepList: LiveData<List<Cep>> get() = _remoteCepList

    private val _localCepList = MutableLiveData<List<Cep>>()
    val localCepList: LiveData<List<Cep>> get() = _localCepList

    init {
        getFavoriteItems()
    }

    suspend fun getCepList(state: String, city: String, street: String){
        _remoteCepList.value = getCepUseCase.invoke(state, city, street)
        Log.d("ViewModel GetRemote", "${_remoteCepList.value!!.size}")
    }

    suspend fun getDataFromCep(cep: String){
        _remoteCepList.value = emptyList()
        _remoteCepList.value = listOf(getDataFromCepUseCase.invoke(cep))
        Log.d("ViewModel Get Remote", "${_remoteCepList.value!!.size}")
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