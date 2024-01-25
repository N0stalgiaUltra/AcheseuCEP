package com.n0stalgiaultra.utils

import com.n0stalgiaultra.data.local.CepLocal
import com.n0stalgiaultra.data.remote.CepDto
fun convertToLocal(item: com.n0stalgiaultra.data.remote.CepDto): CepLocal{
    return CepLocal(
        cep = item.cep,
        logradouro = item.logradouro,
        complemento = item.complemento,
        bairro = item.bairro,
        localidade = item.localidade,
        uf = item.uf,
        ibge = item.ibge,
        gia = item.gia,
        ddd = item.ddd,
        siafi = item.siafi)
}

fun convertToDto(item: CepLocal): com.n0stalgiaultra.data.remote.CepDto {
    return com.n0stalgiaultra.data.remote.CepDto(
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