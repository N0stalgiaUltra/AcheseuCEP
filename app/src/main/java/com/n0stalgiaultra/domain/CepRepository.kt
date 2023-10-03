package com.n0stalgiaultra.domain

import com.n0stalgiaultra.data.remote.CepDto

interface CepRepository{
    suspend fun getRemoteCep(
        state: String,
        city: String,
        street: String
    ): List<CepDto>

    suspend fun getCepData(cep: String): CepDto
}