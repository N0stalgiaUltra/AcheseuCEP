package com.n0stalgiaultra.domain.usecase

import com.n0stalgiaultra.data.remote.CepDto
import com.n0stalgiaultra.domain.CepRepository

class GetCepUseCase (private val repository: CepRepository){
    suspend operator fun invoke(
        state: String,
        city: String,
        street: String): List<CepDto>{

        return repository.getRemoteCep(
            state,
            city,
            street)
    }
}