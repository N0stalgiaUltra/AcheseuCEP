package com.n0stalgiaultra.domain.usecase

import com.n0stalgiaultra.data.local.CepLocal
import com.n0stalgiaultra.data.remote.CepDto
import com.n0stalgiaultra.domain.CepRepository

class GetFavoriteDataUseCase (private val repository: CepRepository) {
    suspend operator fun invoke(): List<CepLocal> {
        return repository.getAllFavourites()
    }
}