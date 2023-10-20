package com.n0stalgiaultra.di

import androidx.room.Room
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.n0stalgiaultra.data.local.AppDatabase
import com.n0stalgiaultra.data.local.CepDao
import com.n0stalgiaultra.data.local.CepLocalDataSourceImpl
import com.n0stalgiaultra.data.remote.CepAPI
import com.n0stalgiaultra.data.remote.CepRemoteDataSourceImpl
import com.n0stalgiaultra.domain.CepLocalDataSource
import com.n0stalgiaultra.domain.CepRemoteDataSource
import com.n0stalgiaultra.domain.CepRepository
import com.n0stalgiaultra.domain.CepRepositoryImpl
import com.n0stalgiaultra.view.viewmodel.MainViewModel
import okhttp3.Interceptor
import okhttp3.OkHttp
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.scope.get
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val retrofitModule = module{
    factory <Interceptor>{
        Interceptor{
            chain ->
            val request  = chain.request()
                .newBuilder()
                .build()
                chain.proceed(request)
        }
    }

    factory <HttpLoggingInterceptor>{
        HttpLoggingInterceptor(
            HttpLoggingInterceptor.Logger.DEFAULT
        ).setLevel(
            HttpLoggingInterceptor.Level.HEADERS
        )
    }

    factory {
        OkHttpClient.Builder().apply {
            addInterceptor( get<Interceptor>() )
            addInterceptor(get<HttpLoggingInterceptor>())
        }.build()
    }

    single {
        Retrofit.Builder()
            .client(get())
            .baseUrl(CepAPI.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
    }
}

val apiModule = module {
    single(createdAtStart = false){
        get<Retrofit>().create(CepAPI::class.java)
    }
}

val databaseModule = module {
    single {
        Room.databaseBuilder(
            context = androidContext(),
            klass = AppDatabase::class.java,
            name = AppDatabase.DATABASE_NAME
            ).build()
    }

    single {
        get<AppDatabase>().getDao()
    }
}

val remoteDataSource = module{
    single<CepRemoteDataSource>{
        CepRemoteDataSourceImpl( api = get<CepAPI>())
    }
}

val localDataSource = module {
    single<CepLocalDataSource>{
        CepLocalDataSourceImpl(dao = get<CepDao>())
    }
}

val repositoryModule = module {
    single <CepRepository>{
        CepRepositoryImpl(
            localDataSource = get<CepLocalDataSource>(),
            remoteDataSource = get<CepRemoteDataSource>())
    }
}

val mainViewModelModule = module {
    viewModel {
        MainViewModel(repository = get<CepRepository>())
    }
}