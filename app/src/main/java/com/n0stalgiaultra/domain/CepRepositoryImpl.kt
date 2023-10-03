package com.n0stalgiaultra.domain

import android.util.Log
import androidx.lifecycle.LiveData
import com.n0stalgiaultra.data.remote.CepDto

class CepRepositoryImpl(private val remoteDataSource: CepRemoteDataSource): CepRepository {
    override suspend fun getRemoteCep(state: String, city: String, street: String): List<CepDto> {
        return remoteDataSource.getRemoteCep(state, city, street)
    }

    override suspend fun getCepData(cep: String): CepDto {
        return remoteDataSource.getCepData(cep)
    }
}