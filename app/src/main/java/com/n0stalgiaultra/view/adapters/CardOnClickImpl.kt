package com.n0stalgiaultra.view.adapters

import com.n0stalgiaultra.data.local.CepLocal
import com.n0stalgiaultra.data.remote.CepDto
import com.n0stalgiaultra.view.viewmodel.MainViewModel

class CardOnClickImpl(private val viewModel: MainViewModel): CardOnClick {

    override suspend fun favoriteItem(item: com.n0stalgiaultra.data.remote.CepDto) {
        viewModel.favoriteItem(item)
    }

    //itens locais
    override suspend fun unFavoriteItem(item: CepLocal) {
        viewModel.unFavoriteItem(item)
    }

    //itens remotos
    override suspend fun unFavoriteItem(item: com.n0stalgiaultra.data.remote.CepDto) {
        viewModel.unFavoriteItem(item)
    }
}