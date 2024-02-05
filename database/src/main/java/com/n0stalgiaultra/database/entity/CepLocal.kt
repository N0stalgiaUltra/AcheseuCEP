package com.n0stalgiaultra.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.n0stalgiaultra.domain.mapper.Cep
import com.n0stalgiaultra.domain.model.CepDto

@Entity
data class CepLocal(
    @PrimaryKey val cep: String,
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
        fun mapLocalToCep(cepLocal: CepLocal): Cep {
            return Cep(
                cepLocal.cep,
                cepLocal.logradouro,
                cepLocal.bairro,
                cepLocal.localidade,
                cepLocal.uf
            )
        }
    }
}

