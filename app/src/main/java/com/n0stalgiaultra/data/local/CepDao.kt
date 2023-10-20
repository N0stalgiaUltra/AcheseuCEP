package com.n0stalgiaultra.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface CepDao {
    @Insert
    fun insertCard(card: CepLocal)

    @Query("SELECT * FROM CepLocal where card_id = :id")
    fun getCard(id: Int) : CepLocal

    @Query("SELECT * FROM CepLocal")
    fun getAllCards() : List<CepLocal>

    @Query("DELETE FROM CepLocal where card_id = :id")
    fun deleteCard(id: Int)

}