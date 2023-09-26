package com.n0stalgiaultra.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CepLocal(
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
    @ColumnInfo("card_id")
    @PrimaryKey(autoGenerate = true) var id: Int = 0
}
