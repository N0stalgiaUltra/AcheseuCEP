package com.n0stalgiaultra.view.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.n0stalgiaultra.domain.mapper.Cep
import com.n0stalgiaultra.myapplication.databinding.CardItemBinding

class CardAdapter(private val cardOnClick: CardOnClick,
                  private val isLocal: Boolean = false): RecyclerView.Adapter<CardViewHolder>(){

    private var _remoteData = emptyList<Cep>()
    private var _localData = emptyList<Cep>()
    fun clearData(){
        _remoteData = emptyList<Cep>()
    }

    fun setData(data: List<Cep>){
        _remoteData = data
    }
    fun setLocalData(data: List<Cep>){
        _localData = data
    }

    private fun checkFavorite(data: Cep): Boolean{
        for (localItem in _localData){
            if(data.cep == localItem.cep)
                return true
        }
        return false
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = CardItemBinding.inflate(inflater, parent, false)
        return CardViewHolder(binding, cardOnClick)
    }

    override fun getItemCount(): Int {
        return _remoteData.size + _localData.size
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {

        if(isLocal){
            if(_localData.isNotEmpty()){
                val data = _localData[position]
                Log.d("CardAdapter", data.logradouro)
                holder.bindData(data, true)
            }
        }
        else{
            if(_remoteData.isNotEmpty()){
                val data = _remoteData[position]
                val fav = checkFavorite(data)
                Log.d("CardAdapter", data.logradouro)
                holder.bindData(data, fav)
            }
        }

    }
}


// Favoritar
// Remoto -> Mostra quem está no Local, e possibilita a chance de adc e remover
// Local -> Só remove