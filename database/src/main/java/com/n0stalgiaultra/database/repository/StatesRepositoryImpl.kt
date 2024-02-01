package com.n0stalgiaultra.database.repository

import com.n0stalgiaultra.database.entity.StatesLocal
import com.n0stalgiaultra.domain.datasources.StatesLocalDataSource
import com.n0stalgiaultra.domain.repository.StatesRepository

class StatesRepositoryImpl(
    private val localDataSource: StatesLocalDataSource
): StatesRepository {

    override suspend fun insertStatesList() {
        for(state in statesList){
            localDataSource.insert(StatesLocal(state))
        }
    }

    override suspend fun getStatesList(): List<String> {
        return localDataSource.getItems()
    }

    companion object{
        val statesList = listOf(
            "AC", "AL", "AP", "AM", "BA", "CE",
            "DF", "ES", "GO", "MA", "MT", "MS", "MG",
            "PA", "PB", "PR", "PE", "PI","RJ","RN",
            "RS","RO", "RR", "SC", "SP", "SE", "TO"
        )
    }
}