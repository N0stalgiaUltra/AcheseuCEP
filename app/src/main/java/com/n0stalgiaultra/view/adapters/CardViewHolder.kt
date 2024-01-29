package com.n0stalgiaultra.view.adapters

import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.n0stalgiaultra.database.entity.CepLocal
import com.n0stalgiaultra.domain.mapper.Cep
import com.n0stalgiaultra.domain.model.CepDto
import com.n0stalgiaultra.myapplication.R
import com.n0stalgiaultra.myapplication.databinding.CardItemBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CardViewHolder(
    private val cardItem : CardItemBinding,
    private val cardOnClick: CardOnClick
): RecyclerView.ViewHolder(cardItem.root) {
    /* Se o objeto pertence ao _localList -> a lista de favoritos*/
    val favoriteIcons = listOf(R.drawable.baseline_star_border_24, R.drawable.baseline_star_24)
    fun bindData(data: Cep, isFav: Boolean) {
        cardItem.btnFavorite.setImageResource(favoriteIcons[if (isFav) 1 else 0])


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
                if (isFav) {
                    cardOnClick.unFavoriteItem(data)
                    cardItem.btnFavorite.setImageResource(favoriteIcons[0])
                    Toast.makeText(
                        cardItem.root.context,
                        "Removido dos favoritos",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    cardOnClick.favoriteItem(data)
                    cardItem.btnFavorite.setImageResource(favoriteIcons[1])
                    Toast.makeText(
                        cardItem.root.context,
                        "Adicionado aos favoritos",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }
}
//
//        when(data){
//            is CepDto -> {
//                cardItem.cardCep.text = data.cep
//                cardItem.cardStreet.text = data.logradouro
//                cardItem.cardNeighbourhood.text = data.bairro
//
//                cardItem.cardCityState.text = cardItem.root.context.getString(
//                    R.string.cidade_estado,
//                    data.localidade,
//                    data.uf
//                )
//
//                cardItem.btnFavorite.setOnClickListener {
//                    CoroutineScope(Dispatchers.Main).launch {
//                        if(isFav) {
//                            cardOnClick.unFavoriteItem(data)
//                            cardItem.btnFavorite.setImageResource(favoriteIcons[0])
//                            Toast.makeText(
//                                cardItem.root.context,
//                                "Removido dos favoritos",
//                                Toast.LENGTH_SHORT).show()
//
//                        }
//                        else {
//                            cardOnClick.favoriteItem(data)
//                            cardItem.btnFavorite.setImageResource(favoriteIcons[1])
//                            Toast.makeText(
//                                cardItem.root.context,
//                                "Adicionado aos favoritos",
//                                Toast.LENGTH_SHORT).show()
//                        }
//                    }
//                }
//
//            }
//            is CepLocal -> {
//                cardItem.cardCep.text = data.cep
//                cardItem.cardStreet.text = data.logradouro
//                cardItem.cardNeighbourhood.text = data.bairro
//
//                cardItem.cardCityState.text = cardItem.root.context.getString(
//                    R.string.cidade_estado,
//                    data.localidade,
//                    data.uf
//                )
//
//                // Adicione lógica para lidar com itens locais, como desfavoritar
//                cardItem.btnFavorite.setOnClickListener {
//                    CoroutineScope(Dispatchers.Main).launch {
//                        if(isFav) {
//                            cardOnClick.unFavoriteItem(data)
//                            cardItem.btnFavorite.setImageResource(favoriteIcons[0])
//                            Toast.makeText(
//                                cardItem.root.context,
//                                "Removido dos favoritos",
//                                Toast.LENGTH_SHORT).show()
//                        }
//                    }
//                }
//            }
//        }
//
//
//    }


