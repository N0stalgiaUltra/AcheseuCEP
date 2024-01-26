package com.n0stalgiaultra.database.utils


import com.n0stalgiaultra.domain.model.CepDto
import com.n0stalgiaultra.database.entity.CepLocal
import com.n0stalgiaultra.domain.mapper.Cep

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
fun checkEmptyData(item: Cep) : Boolean{
    return !(item.cep.isEmpty()
            || item.logradouro.isEmpty()
            || item.bairro.isEmpty()
            || item.localidade.isEmpty()
            || item.uf.isEmpty())
}