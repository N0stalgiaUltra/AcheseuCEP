package com.n0stalgiaultra.view.adapters

import androidx.recyclerview.widget.RecyclerView
import com.n0stalgiaultra.data.remote.CepDto
import com.n0stalgiaultra.myapplication.R
import com.n0stalgiaultra.myapplication.databinding.CardItemBinding

class CardViewHolder(
    private val cardItem : CardItemBinding
): RecyclerView.ViewHolder(cardItem.root) {

    fun bindData(data: CepDto){
        cardItem.cardCep.text = data.cep
        cardItem.cardStreet.text = data.logradouro
        cardItem.cardNeighbourhood.text = data.bairro

        cardItem.cardCityState.text = cardItem.root.context.getString(
            R.string.cidade_estado,
            data.localidade,
            data.uf
        )
    }
}