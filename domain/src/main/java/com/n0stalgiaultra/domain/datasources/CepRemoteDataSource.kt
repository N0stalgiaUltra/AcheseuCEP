package com.n0stalgiaultra.domain.datasources

import com.n0stalgiaultra.domain.mapper.Cep

interface CepRemoteDataSource {
    suspend fun getRemoteCep(
        state: String,
        city: String,
        street: String): List<Cep>

    suspend fun getCepData(cep: String): Cep
}