package com.n0stalgiaultra.domain

import com.n0stalgiaultra.data.local.CepLocal
import com.n0stalgiaultra.data.remote.CepDto

interface CepRepository{
    suspend fun getRemoteCep(
        state: String,
        city: String,
        street: String
    ): List<CepDto>

    suspend fun getCepData(cep: String): CepDto

    suspend fun insertLocalData(item: CepDto)

    suspend fun removeLocalData(item: CepDto)

    suspend fun getAllFavourites() : List<CepLocal>
}