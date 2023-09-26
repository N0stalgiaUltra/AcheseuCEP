package com.n0stalgiaultra.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [CepLocal::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun getDao(): CepDao
    companion object{
        const val DATABASE_NAME = "cep-app-db"
    }
}
