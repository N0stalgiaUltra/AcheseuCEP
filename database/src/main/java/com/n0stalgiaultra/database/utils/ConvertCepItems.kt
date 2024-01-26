package com.n0stalgiaultra.database.utils

import com.n0stalgiaultra.domain.model.CepDto
import com.n0stalgiaultra.database.entity.CepLocal
import com.n0stalgiaultra.domain.mapper.Cep

fun convertToLocal(item: Cep): CepLocal{
    return CepLocal(
        cep = item.cep,
        logradouro = item.logradouro,
        bairro = item.bairro,
        localidade = item.localidade,
        uf = item.uf,
        ibge = "",
        gia = "",
        ddd = "",
        siafi = "",
        complemento = "")
}

fun convertToDto(item: CepLocal): CepDto {
    return CepDto(
        cep = item.cep,
        logradouro = item.logradouro,
        complemento = item.complemento,
        bairro = item.bairro,
        localidade = item.localidade,
        uf = item.uf,
        ibge = item.ibge,
        gia = item.gia,
        ddd = item.ddd,
        siafi = item.siafi
    )
}