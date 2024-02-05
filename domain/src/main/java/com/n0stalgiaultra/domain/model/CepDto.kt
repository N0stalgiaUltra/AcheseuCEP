package com.n0stalgiaultra.domain.model

import com.n0stalgiaultra.domain.mapper.Cep

data class CepDto(
    val cep: String,
    val logradouro: String,
    val complemento: String,
    val bairro: String,
    val localidade: String,
    val uf: String,
    val ibge: String,
    val gia: String,
    val ddd: String,
    val siafi: String
){
    companion object{
        fun mapDtoToCep(cepDto: CepDto): Cep {
            return Cep(
                cepDto.cep,
                cepDto.logradouro,
                cepDto.bairro,
                cepDto.localidade,
                cepDto.uf
            )
        }
    }
}
