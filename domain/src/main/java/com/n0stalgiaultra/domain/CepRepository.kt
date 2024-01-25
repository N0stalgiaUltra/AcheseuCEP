package com.n0stalgiaultra.domain

import com.n0stalgiaultra.data.local.CepLocal
import com.n0stalgiaultra.data.remote.CepDto

interface CepRepository{
    suspend fun getRemoteCep(
        state: String,
        city: String,
        street: String
    ): List<com.n0stalgiaultra.data.remote.CepDto>

    suspend fun getCepData(cep: String): com.n0stalgiaultra.data.remote.CepDto

    suspend fun insertLocalData(item: com.n0stalgiaultra.data.remote.CepDto)

    suspend fun removeLocalData(item: CepLocal)
    suspend fun removeLocalData(item: com.n0stalgiaultra.data.remote.CepDto)

    suspend fun getAllFavourites() : List<CepLocal>
}