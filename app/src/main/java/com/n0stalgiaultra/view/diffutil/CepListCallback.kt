package com.n0stalgiaultra.view.diffutil

import androidx.recyclerview.widget.DiffUtil
import com.n0stalgiaultra.domain.mapper.Cep

class CepListCallback(
    private val oldList: List<Cep>,
    private val newList: List<Cep>
): DiffUtil.Callback() {

    /*Recupera o tamanho das listas*/
    override fun getOldListSize() = oldList.size
    override fun getNewListSize() = newList.size

    //Verifica se os itens são os mesmos
    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].cep == newList[newItemPosition].cep
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].cep == newList[newItemPosition].cep
    }
}