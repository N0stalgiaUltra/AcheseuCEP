package com.n0stalgiaultra.database.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.room.migration.Migration
import com.n0stalgiaultra.database.dao.CepDao
import com.n0stalgiaultra.database.dao.StatesDao
import com.n0stalgiaultra.database.entity.CepLocal
import com.n0stalgiaultra.database.entity.StatesLocal
import com.n0stalgiaultra.database.utils.StringListConverter

@TypeConverters(StringListConverter::class)
@Database(entities = [CepLocal::class, StatesLocal::class], version = 2)
abstract class AppDatabase: RoomDatabase() {
    abstract fun getDao(): CepDao
    abstract fun getStatesDao(): StatesDao

    companion object{
        const val DATABASE_NAME = "cep-app-db"

    }
}


