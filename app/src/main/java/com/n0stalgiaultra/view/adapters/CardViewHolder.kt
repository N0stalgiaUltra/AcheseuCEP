package com.n0stalgiaultra.view.adapters

import android.util.Log
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
    private val favoriteIcons = listOf(R.drawable.baseline_star_border_24, R.drawable.baseline_star_24)
    private var isFavorite = false
    fun bindData(data: Cep, isFav: Boolean) {
        isFavorite = isFav
        cardItem.btnFavorite.setImageResource(favoriteIcons[if (isFavorite) 1 else 0])

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
                if (isFavorite) {
                    cardItem.btnFavorite.setImageResource(favoriteIcons[0])
                    cardOnClick.unFavoriteItem(data)
                    isFavorite = false
                    Log.d("ViewHolder", isFavorite.toString())

                    Toast.makeText(
                        cardItem.root.context,
                        "Removido dos favoritos",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    cardItem.btnFavorite.setImageResource(favoriteIcons[1])
                    cardOnClick.favoriteItem(data)
                    isFavorite = true
                    Log.d("ViewHolder", isFavorite.toString())

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

// false -> favorita
// true -> desfavorita -> remove do BD, tenta remover 2x
// false -> desfavorita