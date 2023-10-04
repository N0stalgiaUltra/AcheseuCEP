package com.n0stalgiaultra.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.n0stalgiaultra.data.remote.CepDto
import com.n0stalgiaultra.myapplication.databinding.CardItemBinding

class CardAdapter: RecyclerView.Adapter<CardViewHolder>() {

    private var _data = emptyList<CepDto>()

    fun clearData(){
        _data = emptyList()
    }

    fun setData(data: List<CepDto>){
        _data = data
    }
    fun setData(item: CepDto){
        _data = _data + item
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = CardItemBinding.inflate(inflater, parent, false)
        return CardViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return _data.size
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        val data = _data[position]
        holder.bindData(data)
    }
}