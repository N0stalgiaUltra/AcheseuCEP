package com.n0stalgiaultra.domain

import androidx.lifecycle.LiveData
import com.n0stalgiaultra.data.remote.CepDto
import com.n0stalgiaultra.data.remote.CepRemoteDataSourceImpl

interface CepRepository{
    suspend fun getRemoteData(): List<CepDto>
}