package com.n0stalgiaultra.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.n0stalgiaultra.database.entity.StatesLocal
import com.n0stalgiaultra.domain.entities.States

@Dao
interface StatesDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(data: StatesLocal)

    @Query("SELECT * from StatesLocal")
    fun getStates() : List<StatesLocal>
}