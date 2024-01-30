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
        _remoteData = emptyList()
    }

    fun setRemoteData(data: List<Cep>){
        _remoteData = data
    }
    fun setLocalData(data: List<Cep>){
        _localData = data
        Log.d("CardAdapter", _localData.size.toString())
    }

    private fun checkFavorite(data: Cep): Boolean{
        Log.d("CardAdapter", "Entrando no CheckFav")
        Log.d("CardAdapter", _localData.size.toString())

        for (localItem in _localData){
            Log.d("CardAdapter", localItem.cep)
            if(data.cep == localItem.cep) {
                data.id = localItem.id
                return true
            }
        }
        return false
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = CardItemBinding.inflate(inflater, parent, false)
        return CardViewHolder(binding, cardOnClick)
    }

    override fun getItemCount(): Int {
        if(isLocal)
            return _localData.size
        else
            return _remoteData.size
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        val data = if (isLocal) {
            _localData[position]
        } else {
            _remoteData[position]
        }

        val fav = checkFavorite(data)
        holder.bindData(data, fav)
//        if(_localData.isNotEmpty()){
//            val data = _localData[position]
//            holder.bindData(data, true)
//        }
//        else if(_remoteData.isNotEmpty()){
//            val data = _remoteData[position]
//            Log.d("CardAdapter", data.cep.toString() + " " + data.logradouro)
//
//            val fav = checkFavorite(data)
//            Log.d("CardAdapter", fav.toString())
//            holder.bindData(data, fav)
//        }
//        else return
    }
}


// Favoritar
// Remoto -> Mostra quem está no Local, e possibilita a chance de adc e remover
// Local -> Só remove