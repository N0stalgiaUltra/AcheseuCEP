package com.n0stalgiaultra.domain.mapper

import com.n0stalgiaultra.domain.model.CepDto

data class Cep(
    val id : Int? = null, //ID do Banco de Dados
    val cep: String,
    val logradouro: String,
    val bairro: String,
    val localidade: String,
    val uf: String,
)



