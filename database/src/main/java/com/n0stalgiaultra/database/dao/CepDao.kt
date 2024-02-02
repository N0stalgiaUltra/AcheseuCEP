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

    @Query("SELECT * FROM CepLocal where card_id = :id")
    fun getCard(id: Int) : CepLocal

    @Query("SELECT * FROM CepLocal")
    fun getAllCards() : List<CepLocal>

    @Query("DELETE FROM CepLocal where card_id = :id")
    fun deleteCard(id: Int)

}