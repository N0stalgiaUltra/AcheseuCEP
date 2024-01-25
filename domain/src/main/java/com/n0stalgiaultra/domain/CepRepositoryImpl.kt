package com.n0stalgiaultra.domain

import com.n0stalgiaultra.data.local.CepLocal
import com.n0stalgiaultra.data.remote.CepDto

class CepRepositoryImpl(
    private val localDataSource: CepLocalDataSource,
    private val remoteDataSource: CepRemoteDataSource
): CepRepository {
    override suspend fun getRemoteCep(state: String, city: String, street: String): List<com.n0stalgiaultra.data.remote.CepDto> {
        return remoteDataSource.getRemoteCep(state, city, street)
    }

    override suspend fun getCepData(cep: String): com.n0stalgiaultra.data.remote.CepDto {
        return remoteDataSource.getCepData(cep)
    }

    override suspend fun insertLocalData(item: com.n0stalgiaultra.data.remote.CepDto) {
        localDataSource.insert(item)
    }

    override suspend fun removeLocalData(item: CepLocal) {
        localDataSource.remove(item)
    }
    override suspend fun removeLocalData(item: com.n0stalgiaultra.data.remote.CepDto) {
        localDataSource.remove(item)
    }

    override suspend fun getAllFavourites(): List<CepLocal> {
        return localDataSource.getItems()
    }
}