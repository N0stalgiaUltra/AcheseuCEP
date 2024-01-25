package com.n0stalgiaultra.view.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.n0stalgiaultra.data.local.CepLocal
import com.n0stalgiaultra.data.remote.CepDto
import com.n0stalgiaultra.myapplication.databinding.CardItemBinding

class CardAdapter(private val cardOnClick: CardOnClick,
                  private val isLocal: Boolean = false): RecyclerView.Adapter<CardViewHolder>(){

    private var _data = emptyList<com.n0stalgiaultra.data.remote.CepDto>()
    private var _localData = emptyList<CepLocal>()
    fun clearData(){
        //_localData = emptyList()
        _data = emptyList()
    }

    fun setData(data: List<com.n0stalgiaultra.data.remote.CepDto>){
        _data = data
    }
    fun setLocalData(data: List<CepLocal>){
        _localData = data
    }

    private fun checkFavorite(data: com.n0stalgiaultra.data.remote.CepDto): Boolean{
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
        return _data.size + _localData.size
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
            if(_data.isNotEmpty()){
                val data = _data[position]
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