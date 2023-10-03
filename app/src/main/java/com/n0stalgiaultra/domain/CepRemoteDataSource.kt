package com.n0stalgiaultra.domain

import androidx.lifecycle.LiveData
import com.n0stalgiaultra.data.remote.CepDto
import com.n0stalgiaultra.data.remote.CepResponseItem

interface CepRemoteDataSource {
    suspend fun getRemoteCep(
        state: String,
        city: String,
        street: String): List<CepDto>

    suspend fun getCepData(cep: String): CepDto
}