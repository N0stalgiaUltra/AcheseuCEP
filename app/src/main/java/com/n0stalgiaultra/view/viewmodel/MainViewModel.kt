package com.n0stalgiaultra.view.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.n0stalgiaultra.domain.mapper.Cep
import com.n0stalgiaultra.domain.usecase.cep.FavoriteCepUseCase
import com.n0stalgiaultra.domain.usecase.cep.GetCepUseCase
import com.n0stalgiaultra.domain.usecase.cep.GetDataFromCepUseCase
import com.n0stalgiaultra.domain.usecase.cep.GetFavoriteDataUseCase
import com.n0stalgiaultra.domain.usecase.cep.UnfavoriteCepUseCase
import com.n0stalgiaultra.domain.usecase.states.GetStatesUseCase
import com.n0stalgiaultra.domain.usecase.states.InsertStatesUseCase
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
    private val getFavoriteDataUseCase: GetFavoriteDataUseCase,
    private val getStatesUseCase: GetStatesUseCase,
    private val insertStatesUseCase: InsertStatesUseCase
): ViewModel(){

    private val _remoteCepList = MutableLiveData<List<Cep>>()
    val remoteCepList: LiveData<List<Cep>> get() = _remoteCepList

    private val _localCepList = MutableLiveData<List<Cep>>()
    val localCepList: LiveData<List<Cep>> get() = _localCepList

    private val _statesList = MutableLiveData<List<String>>()
    val statesList: LiveData<List<String>> get() = _statesList

    //TODO: SEPARAR A VIEW MODEL PARA CADA FUNCIONALIDADE
    init {
        getFavoriteItems()
        getStatesList()
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

    @OptIn(DelicateCoroutinesApi::class)
    fun getStatesList(){
        _statesList.value = listOf()

        if(!_statesList.value.isNullOrEmpty()) {
            GlobalScope.launch {
                withContext(Dispatchers.Main){
                    val values = getStatesUseCase.invoke()
                    _statesList.postValue(values)
                    Log.d("States List", "${_statesList.value!!.size}")

                }
            }
        }
        else{
            GlobalScope.launch {
                insertStatesUseCase.invoke()
                Log.d("States List", "${_statesList.value!!.size}")

            }
        }
    }


}

// 1- verifica se o banco retorna a lista de estados
// 2- se não, insere e verifica novamente
// 3- se sim, retorna a lista.