package com.n0stalgiaultra.utils

import com.n0stalgiaultra.data.local.CepLocal
import com.n0stalgiaultra.data.remote.CepDto

fun checkEmptyData(item: CepDto) : Boolean{
    return !(item.cep.isEmpty()
            || item.logradouro.isEmpty()
            || item.bairro.isEmpty()
            || item.localidade.isEmpty()
            || item.uf.isEmpty())
}

fun checkEmptyData(item: CepLocal) : Boolean{
    return !(item.cep.isEmpty()
            || item.logradouro.isEmpty()
            || item.bairro.isEmpty()
            || item.localidade.isEmpty()
            || item.uf.isEmpty())
}