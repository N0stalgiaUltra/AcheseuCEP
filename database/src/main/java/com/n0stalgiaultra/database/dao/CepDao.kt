package com.n0stalgiaultra.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.n0stalgiaultra.database.entity.CepLocal

@Dao
interface CepDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertCard(card: CepLocal)

    @Query("SELECT * FROM CepLocal where cep = :cep")
    fun getCard(cep: String) : CepLocal

    @Query("SELECT * FROM CepLocal")
    fun getAllCards() : List<CepLocal>

    @Query("DELETE FROM CepLocal where cep = :cep")
    fun deleteCard(cep: String)

}