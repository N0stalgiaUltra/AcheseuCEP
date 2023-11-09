package com.n0stalgiaultra.domain

import com.n0stalgiaultra.data.local.CepLocal
import com.n0stalgiaultra.data.remote.CepDto

interface CepLocalDataSource {

    fun insert(item: CepDto)
    fun remove(item: CepLocal)

    fun remove(item: CepDto)

    fun getItems() : List<CepLocal>

}