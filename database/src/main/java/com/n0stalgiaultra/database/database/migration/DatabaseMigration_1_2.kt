package com.n0stalgiaultra.database.database.migration

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

class DatabaseMigration_2_3 (startVersion: Int, endVersion: Int)
    : Migration(startVersion, endVersion){
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL("CREATE TABLE IF NOT EXISTS StatesLocal " +
                "(`states` TEXT NOT NULL, " +
                "`state_id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL)")

        // Exclusão da tabela CepLocal
        database.execSQL("DROP TABLE IF EXISTS CepLocal")

        // Criação da nova tabela CepLocal com o CEP como chave primária
        database.execSQL("CREATE TABLE IF NOT EXISTS CepLocal" +
                "(`uf` TEXT NOT NULL," +
                "`complemento` TEXT NOT NULL," +
                "`ddd` TEXT NOT NULL," +
                "`logradouro` TEXT NOT NULL," +
                "`bairro` TEXT NOT NULL," +
                "`localidade` TEXT NOT NULL," +
                "`ibge` TEXT NOT NULL," +
                "`siafi` TEXT NOT NULL," +
                "`gia` TEXT NOT NULL,"  +
                "`cep` TEXT PRIMARY KEY NOT NULL)"

        )


    }

}