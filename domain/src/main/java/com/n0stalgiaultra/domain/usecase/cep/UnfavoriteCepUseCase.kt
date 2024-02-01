package com.n0stalgiaultra.domain.usecase.cep

import com.n0stalgiaultra.domain.mapper.Cep
import com.n0stalgiaultra.domain.repository.CepRepository

class UnfavoriteCepUseCase (private val repository: CepRepository) {
    suspend operator fun invoke(item: Cep){
        repository.removeLocalData(item)
    }
}