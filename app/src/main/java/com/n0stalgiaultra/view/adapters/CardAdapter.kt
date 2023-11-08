package com.n0stalgiaultra.view.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.n0stalgiaultra.data.local.CepLocal
import com.n0stalgiaultra.data.remote.CepDto
import com.n0stalgiaultra.myapplication.databinding.CardItemBinding

class CardAdapter(private val cardOnClick: CardOnClick): RecyclerView.Adapter<CardViewHolder>(){

    private var _data = emptyList<CepDto>()
    private var _localData = emptyList<CepLocal>()
    fun clearData(){
        _localData = emptyList()
        _data = emptyList()
    }

    fun setData(data: List<CepDto>){
        _data = data
    }
    fun setLocalData(data: List<CepLocal>){
        _localData = data
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
        if(_data.isNotEmpty()){
            val data = _data[position]
            Log.d("CardAdapter", data.logradouro)
            holder.bindData(data)
        }

        if(_localData.isNotEmpty()){
            val data = _localData[position]
            Log.d("CardAdapter", data.logradouro)
            holder.bindData(data)
        } else{ Log.d("CardAdapter", "localData is Empty")}

    }
}