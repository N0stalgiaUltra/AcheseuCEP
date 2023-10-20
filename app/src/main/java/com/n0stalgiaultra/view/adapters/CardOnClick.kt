package com.n0stalgiaultra.view.adapters

import com.n0stalgiaultra.data.remote.CepDto

interface CardOnClick {
    suspend fun favoriteItem(item: CepDto)
    suspend fun unFavoriteItem(item: CepDto)
}