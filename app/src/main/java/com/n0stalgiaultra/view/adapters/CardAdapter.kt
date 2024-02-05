package com.n0stalgiaultra.view.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.n0stalgiaultra.domain.mapper.Cep
import com.n0stalgiaultra.myapplication.databinding.CardItemBinding

class CardAdapter(private val cardOnClick: CardOnClick,
                  private val isLocal: Boolean = false): RecyclerView.Adapter<CardViewHolder>(){

    private var _remoteData = emptyList<Cep>()
    private var _localData = emptyList<Cep>()


    private val diffUtil = object : DiffUtil.ItemCallback<Cep>(){
        override fun areItemsTheSame(oldItem: Cep, newItem: Cep): Boolean {
            return oldItem.cep == newItem.cep
        }

        override fun areContentsTheSame(oldItem: Cep, newItem: Cep): Boolean {
            return oldItem == newItem
        }

    }

    private val asyncRemoteListDiffer = AsyncListDiffer(this, diffUtil)
    private val asyncLocalListDiffer = AsyncListDiffer(this, diffUtil)

    fun setRemoteData(data: List<Cep>){
        asyncRemoteListDiffer.submitList(data)
    }
    fun setLocalData(data: List<Cep>){
        asyncLocalListDiffer.submitList(data)
    }

    // TODO: MOVER ESSA VERIFICAÇÃO PARA A VIEW MODEL ADEQUADA
    private fun checkFavorite(data: Cep): Boolean{
        for (localItem in asyncLocalListDiffer.currentList){
            if(data.cep == localItem.cep) {
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
            return asyncLocalListDiffer.currentList.size
        else
            return asyncRemoteListDiffer.currentList.size

    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        val data = if (isLocal) {
            asyncLocalListDiffer.currentList[position]
        } else {
            asyncRemoteListDiffer.currentList[position]
        }

        val fav = checkFavorite(data)
        holder.bindData(data, fav)
    }
}