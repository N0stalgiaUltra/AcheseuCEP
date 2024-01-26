package com.n0stalgiaultra.view.adapters

import com.n0stalgiaultra.domain.model.CepDto
import com.n0stalgiaultra.database.entity.CepLocal

interface CardOnClick {
    suspend fun favoriteItem(item: CepDto)
    suspend fun unFavoriteItem(item: CepLocal)
    suspend fun unFavoriteItem(item: CepDto)

}