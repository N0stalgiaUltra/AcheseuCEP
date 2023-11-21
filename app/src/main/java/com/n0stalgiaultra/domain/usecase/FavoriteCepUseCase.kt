package com.n0stalgiaultra.domain.usecase

import com.n0stalgiaultra.data.remote.CepDto
import com.n0stalgiaultra.domain.CepRepository

class FavoriteCepUseCase(private val repository: CepRepository) {
    suspend operator fun invoke(
        item: CepDto
    ){
        repository.insertLocalData(item)
    }
}