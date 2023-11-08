package com.n0stalgiaultra.view.adapters

import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import com.n0stalgiaultra.data.local.CepLocal
import com.n0stalgiaultra.data.remote.CepDto
import com.n0stalgiaultra.myapplication.R
import com.n0stalgiaultra.myapplication.databinding.CardItemBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CardViewHolder(
    private val cardItem : CardItemBinding,
    val cardOnClick: CardOnClick
): RecyclerView.ViewHolder(cardItem.root) {

    fun bindData(data: Any){
        when(data){
            is CepDto -> {
                cardItem.cardCep.text = data.cep
                cardItem.cardStreet.text = data.logradouro
                cardItem.cardNeighbourhood.text = data.bairro

                cardItem.cardCityState.text = cardItem.root.context.getString(
                    R.string.cidade_estado,
                    data.localidade,
                    data.uf
                )

                cardItem.btnFavorite.setOnClickListener {
                    CoroutineScope(Dispatchers.Main).launch {
                        cardOnClick.favoriteItem(data)
                    }
                }
            }
            is CepLocal -> {
                cardItem.cardCep.text = data.cep
                cardItem.cardStreet.text = data.logradouro
                cardItem.cardNeighbourhood.text = data.bairro

                cardItem.cardCityState.text = cardItem.root.context.getString(
                    R.string.cidade_estado,
                    data.localidade,
                    data.uf
                )

                // Adicione lógica para lidar com itens locais, como desfavoritar
            }
        }
    }

//    fun bindRemoteData(data: CepDto){
//        cardItem.cardCep.text = data.cep
//        cardItem.cardStreet.text = data.logradouro
//        cardItem.cardNeighbourhood.text = data.bairro
//
//        cardItem.cardCityState.text = cardItem.root.context.getString(
//            R.string.cidade_estado,
//            data.localidade,
//            data.uf
//        )
//
//        /*TODO: adicionar maneira de controlar caso o item não tenha sido favoritado*/
//        cardItem.btnFavorite.setOnClickListener {
//            CoroutineScope(Dispatchers.Main).launch {
//                cardOnClick.favoriteItem(data)
//            }
//        }
//    }
//
//    fun bindLocalData(data: CepLocal){
//        cardItem.cardCep.text = data.cep
//        cardItem.cardStreet.text = data.logradouro
//        cardItem.cardNeighbourhood.text = data.bairro
//
//        cardItem.cardCityState.text = cardItem.root.context.getString(
//            R.string.cidade_estado,
//            data.localidade,
//            data.uf
//        )
//        Log.d("bindLocalData", "${data.cep}, ${data.logradouro}")
//        /*TODO: adicionar maneira de controlar caso o item não tenha sido favoritado*/
////        cardItem.btnFavorite.setOnClickListener {
////            CoroutineScope(Dispatchers.Main).launch {
////                cardOnClick.unFavoriteItem(data)
////            }
////        }
//    }

}