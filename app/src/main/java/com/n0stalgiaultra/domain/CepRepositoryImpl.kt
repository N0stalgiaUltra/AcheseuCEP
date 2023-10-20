package com.n0stalgiaultra.domain

import android.util.Log
import androidx.lifecycle.LiveData
import com.n0stalgiaultra.data.local.CepLocal
import com.n0stalgiaultra.data.remote.CepDto

class CepRepositoryImpl(
    private val localDataSource: CepLocalDataSource,
    private val remoteDataSource: CepRemoteDataSource): CepRepository {
    override suspend fun getRemoteCep(state: String, city: String, street: String): List<CepDto> {
        return remoteDataSource.getRemoteCep(state, city, street)
    }

    override suspend fun getCepData(cep: String): CepDto {
        return remoteDataSource.getCepData(cep)
    }

    override suspend fun insertLocalData(item: CepDto) {
        localDataSource.insert(item)
    }

    override suspend fun removeLocalData(item: CepDto) {
        localDataSource.remove(item)
    }

    override suspend fun getAllFavourites(): List<CepLocal> {
        return localDataSource.getItems()
    }
}