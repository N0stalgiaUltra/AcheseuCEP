package com.n0stalgiaultra.database.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.n0stalgiaultra.database.dao.CepDao
import com.n0stalgiaultra.database.entity.CepLocal

@Database(entities = [CepLocal::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun getDao(): CepDao
    companion object{
        const val DATABASE_NAME = "cep-app-db"
    }
}

@Database(entities = [CepLocal::class], version = 1)
abstract class TestAppDatabase: RoomDatabase() {
    abstract fun getDao(): CepDao
    companion object{
        const val DATABASE_NAME = "cep-app-db-test"

        fun createInMemoryDatabase(context: Context): TestAppDatabase {
            return Room.inMemoryDatabaseBuilder(
                context,
                TestAppDatabase::class.java)
                .allowMainThreadQueries()
                .build()
        }
    }
}

