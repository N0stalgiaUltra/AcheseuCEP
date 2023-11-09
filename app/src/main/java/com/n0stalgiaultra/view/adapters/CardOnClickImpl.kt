package com.n0stalgiaultra.view.adapters

import com.n0stalgiaultra.data.local.CepLocal
import com.n0stalgiaultra.data.remote.CepDto
import com.n0stalgiaultra.view.viewmodel.MainViewModel

class CardOnClickImpl(private val viewModel: MainViewModel): CardOnClick {

    override suspend fun favoriteItem(item: CepDto) {
        viewModel.favoriteItem(item)
    }

    //itens locais
    override suspend fun unFavoriteItem(item: CepLocal) {
        viewModel.unFavoriteItem(item)
    }

    //itens remotos
    override suspend fun unFavoriteItem(item: CepDto) {
        viewModel.unFavoriteItem(item)
    }
}