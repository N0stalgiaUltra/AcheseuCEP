package com.n0stalgiaultra.view.adapters

import com.n0stalgiaultra.domain.model.CepDto
import com.n0stalgiaultra.database.entity.CepLocal
import com.n0stalgiaultra.domain.mapper.Cep
import com.n0stalgiaultra.view.viewmodel.MainViewModel

class CardOnClickImpl(private val viewModel: MainViewModel): CardOnClick {

    override suspend fun favoriteItem(item: Cep) {
        viewModel.favoriteItem(item)
    }

    override suspend fun unFavoriteItem(item: Cep) {
        viewModel.unFavoriteItem(item)
    }
}