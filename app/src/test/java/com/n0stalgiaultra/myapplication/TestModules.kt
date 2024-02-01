package com.n0stalgiaultra.myapplication

import androidx.room.Room
import com.n0stalgiaultra.data.local.CepLocalDataSourceImpl
import com.n0stalgiaultra.data.local.TestAppDatabase
import com.n0stalgiaultra.domain.datasources.CepLocalDataSource
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

/**
 * NOT WORKING FOR UNIT TESTING
 * Used for local DB tests
 *
 * */
val testDatabaseModule = module{
    single{
        Room.inMemoryDatabaseBuilder(
            androidApplication(),
            TestAppDatabase::class.java)
            .allowMainThreadQueries()
            .build()
    }
    single{
        get<TestAppDatabase>().getDao()
    }

    single<CepLocalDataSource> {
        CepLocalDataSourceImpl(get())
    }
}