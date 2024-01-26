package com.n0stalgiaultra.domain.usecase

import com.n0stalgiaultra.domain.mapper.Cep
import com.n0stalgiaultra.domain.repository.CepRepository

class GetFavoriteDataUseCase (private val repository: CepRepository) {
    suspend operator fun invoke(): List<Cep> {
        return repository.getAllFavourites()
    }
}