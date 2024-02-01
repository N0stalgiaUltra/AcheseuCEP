package com.n0stalgiaultra.domain.usecase.cep

import com.n0stalgiaultra.domain.mapper.Cep
import com.n0stalgiaultra.domain.repository.CepRepository

class GetCepUseCase (private val repository: CepRepository){
    suspend operator fun invoke(
        state: String,
        city: String,
        street: String): List<Cep>{

        return repository.getRemoteCep(
            state,
            city,
            street)
    }
}