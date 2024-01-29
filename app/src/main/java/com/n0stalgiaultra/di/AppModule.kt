package com.n0stalgiaultra.di

import androidx.room.Room
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.n0stalgiaultra.data.remote.CepAPI
import com.n0stalgiaultra.data.repository.CepRemoteDataSourceImpl
import com.n0stalgiaultra.database.dao.CepDao
import com.n0stalgiaultra.database.database.AppDatabase
import com.n0stalgiaultra.database.repository.CepLocalDataSourceImpl
import com.n0stalgiaultra.domain.CepLocalDataSource
import com.n0stalgiaultra.domain.CepRemoteDataSource
import com.n0stalgiaultra.domain.CepRepositoryImpl
import com.n0stalgiaultra.domain.repository.CepRepository
import com.n0stalgiaultra.domain.usecase.FavoriteCepUseCase
import com.n0stalgiaultra.domain.usecase.GetCepUseCase
import com.n0stalgiaultra.domain.usecase.GetDataFromCepUseCase
import com.n0stalgiaultra.domain.usecase.GetFavoriteDataUseCase
import com.n0stalgiaultra.domain.usecase.UnfavoriteCepUseCase
import com.n0stalgiaultra.view.viewmodel.MainViewModel
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


val appModule = module{

    factory <Interceptor>{
        Interceptor{
                chain ->
            val request  = chain.request()
                .newBuilder()
                .build()
            chain.proceed(request)
        }
    }
    //http logging in
    factory <HttpLoggingInterceptor>{
        HttpLoggingInterceptor(
            HttpLoggingInterceptor.Logger.DEFAULT
        ).setLevel(
            HttpLoggingInterceptor.Level.HEADERS
        )
    }
    //okhttp
    factory {
        OkHttpClient.Builder().apply {
            addInterceptor( get<Interceptor>() )
            addInterceptor(get<HttpLoggingInterceptor>())
        }.build()
    }
    //retrofit
    single {
        Retrofit.Builder()
            .client(get())
            .baseUrl(CepAPI.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
    }
    //instancia da API
    single(createdAtStart = false){
        get<Retrofit>().create(CepAPI::class.java)
    }

    //Room
    single {
        Room.databaseBuilder(
            context = androidContext(),
            klass = AppDatabase::class.java,
            name = AppDatabase.DATABASE_NAME
        ).build()
    }

    //Dao
    single {
        get<AppDatabase>().getDao()
    }

    //RemoteDataSource
    single<CepRemoteDataSource>{
        CepRemoteDataSourceImpl(api = get<CepAPI>())
    }

    //LocalDataSource
    single<CepLocalDataSource>{
        CepLocalDataSourceImpl(dao = get<CepDao>())
    }

    //CepRepository
    single <CepRepository>{
        CepRepositoryImpl(
            localDataSource = get<CepLocalDataSource>(),
            remoteDataSource = get<CepRemoteDataSource>()
        )
    }

    //UseCases
    factory { FavoriteCepUseCase(get<CepRepository>()) }
    factory { GetCepUseCase(get<CepRepository>()) }
    factory { GetDataFromCepUseCase(get<CepRepository>()) }
    factory { UnfavoriteCepUseCase(get<CepRepository>()) }
    factory { GetFavoriteDataUseCase(get<CepRepository>()) }

    //ViewModel
    viewModel {
        MainViewModel(
            get<FavoriteCepUseCase>(),
            get<UnfavoriteCepUseCase>(),
            get<GetCepUseCase>(),
            get<GetDataFromCepUseCase>(),
            get<GetFavoriteDataUseCase>()
        )
    }
}
