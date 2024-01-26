package com.n0stalgiaultra.domain

import com.n0stalgiaultra.domain.mapper.Cep


interface CepLocalDataSource {

    fun insert(item: Cep)
    fun remove(item: Cep)
    fun getItems() : List<Cep>

}