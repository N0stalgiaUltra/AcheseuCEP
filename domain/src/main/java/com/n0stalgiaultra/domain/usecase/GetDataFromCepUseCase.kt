package com.n0stalgiaultra.domain.usecase

import com.n0stalgiaultra.data.remote.CepDto
import com.n0stalgiaultra.domain.CepRepository

class GetDataFromCepUseCase(private val repository: CepRepository) {
    suspend operator fun invoke(cep: String): com.n0stalgiaultra.data.remote.CepDto {
        return repository.getCepData(cep)
    }
}