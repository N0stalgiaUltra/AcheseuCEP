package com.n0stalgiaultra.view.adapters

import com.n0stalgiaultra.domain.mapper.Cep

interface CardOnClick {
    suspend fun favoriteItem(item: Cep)
    suspend fun unFavoriteItem(item: Cep)
}