package com.n0stalgiaultra

import android.app.Application
import com.n0stalgiaultra.di.apiModule
import com.n0stalgiaultra.di.mainViewModelModule
import com.n0stalgiaultra.di.remoteDataSource
import com.n0stalgiaultra.di.repositoryModule
import com.n0stalgiaultra.di.retrofitModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MyApp : Application(){

    override fun onCreate() {
        super.onCreate()
        startKoin{
            androidLogger(Level.NONE)
            androidContext(this@MyApp)
            modules(
                retrofitModule,
                apiModule,
                repositoryModule,
                mainViewModelModule,
                remoteDataSource
            )
        }
    }
}