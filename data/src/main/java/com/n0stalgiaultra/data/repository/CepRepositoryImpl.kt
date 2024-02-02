package com.n0stalgiaultra.data.repository

import com.n0stalgiaultra.domain.datasources.CepLocalDataSource
import com.n0stalgiaultra.domain.datasources.CepRemoteDataSource
import com.n0stalgiaultra.domain.mapper.Cep
import com.n0stalgiaultra.domain.repository.CepRepository


class CepRepositoryImpl(
    private val localDataSource: CepLocalDataSource,
    private val remoteDataSource: CepRemoteDataSource
): CepRepository {
    // TODO: SEPARAR REPOSITÓRIO EM LOCAL E REMOTO
    override suspend fun getRemoteCep(state: String, city: String, street: String): List<Cep> {
        return remoteDataSource.getRemoteCep(
            state = state,
            city = city,
            street = street
        )
    }

    override suspend fun getCepData(cep: String): Cep {
        return remoteDataSource.getCepData(cep = cep)
    }

    override suspend fun insertLocalData(item: Cep) {
        return localDataSource.insert(item = item)
    }

    override suspend fun removeLocalData(item: Cep) {
        return localDataSource.remove(item = item)
    }

    override suspend fun getAllFavourites(): List<Cep> {
        return localDataSource.getItems()
    }

}