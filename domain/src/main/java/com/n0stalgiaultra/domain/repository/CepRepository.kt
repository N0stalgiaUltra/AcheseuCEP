package com.n0stalgiaultra.domain.repository

import com.n0stalgiaultra.domain.mapper.Cep

interface CepRepository{
    suspend fun getRemoteCep(
        state: String,
        city: String,
        street: String
    ): List<Cep>

    suspend fun getCepData(cep: String): Cep

    suspend fun insertLocalData(item: Cep)

    suspend fun removeLocalData(item: Cep)

    suspend fun getAllFavourites() : List<Cep>
}