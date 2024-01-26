package com.n0stalgiaultra.domain.usecase

import com.n0stalgiaultra.domain.mapper.Cep
import com.n0stalgiaultra.domain.repository.CepRepository

class GetDataFromCepUseCase(private val repository: CepRepository) {
    suspend operator fun invoke(cep: String): Cep {
        return repository.getCepData(cep)
    }
}