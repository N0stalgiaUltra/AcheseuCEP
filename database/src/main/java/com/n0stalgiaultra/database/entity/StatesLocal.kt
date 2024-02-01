package com.n0stalgiaultra.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.n0stalgiaultra.database.utils.StringListConverter
import com.n0stalgiaultra.domain.entities.States

@Entity
data class StatesLocal(
    @TypeConverters(StringListConverter::class)
    override val states: String
):States{
    @ColumnInfo("state_id")
    @PrimaryKey(autoGenerate = true) var id: Int = 0
}

