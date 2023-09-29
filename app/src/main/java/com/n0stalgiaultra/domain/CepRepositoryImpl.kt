package com.n0stalgiaultra.domain

import android.util.Log
import androidx.lifecycle.LiveData
import com.n0stalgiaultra.data.remote.CepDto

class CepRepositoryImpl(private val remoteDataSource: CepRemoteDataSource): CepRepository {
    override suspend fun getRemoteData(): List<CepDto> {
        return remoteDataSource.getRemoteCep()
    }
}