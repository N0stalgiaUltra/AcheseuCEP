package com.n0stalgiaultra.database.database.migration

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

class DatabaseMigration_1_2 (startVersion: Int, endVersion: Int)
    : Migration(startVersion, endVersion){
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL("CREATE TABLE IF NOT EXISTS StatesLocal " +
                "(`states` TEXT NOT NULL, " +
                "`state_id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL)")

    }

}