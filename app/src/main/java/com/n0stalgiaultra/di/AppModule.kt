package com.n0stalgiaultra.di

import androidx.room.Room
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.n0stalgiaultra.data.remote.CepAPI
import com.n0stalgiaultra.data.repository.CepRemoteDataSourceImpl
import com.n0stalgiaultra.database.dao.CepDao
import com.n0stalgiaultra.database.database.AppDatabase
import com.n0stalgiaultra.database.repository.CepLocalDataSourceImpl
import com.n0stalgiaultra.domain.datasources.CepLocalDataSource
import com.n0stalgiaultra.domain.datasources.CepRemoteDataSource
import com.n0stalgiaultra.data.repository.CepRepositoryImpl
import com.n0stalgiaultra.database.dao.StatesDao
import com.n0stalgiaultra.database.database.migration.DatabaseMigration_1_2
import com.n0stalgiaultra.database.repository.StatesLocalDataSourceImpl
import com.n0stalgiaultra.database.repository.StatesRepositoryImpl
import com.n0stalgiaultra.domain.datasources.StatesLocalDataSource
import com.n0stalgiaultra.domain.repository.CepRepository
import com.n0stalgiaultra.domain.repository.StatesRepository
import com.n0stalgiaultra.domain.usecase.cep.FavoriteCepUseCase
import com.n0stalgiaultra.domain.usecase.cep.GetCepUseCase
import com.n0stalgiaultra.domain.usecase.cep.GetDataFromCepUseCase
import com.n0stalgiaultra.domain.usecase.cep.GetFavoriteDataUseCase
import com.n0stalgiaultra.domain.usecase.cep.UnfavoriteCepUseCase
import com.n0stalgiaultra.domain.usecase.states.GetStatesUseCase
import com.n0stalgiaultra.domain.usecase.states.InsertStatesUseCase
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
        ).addMigrations(DatabaseMigration_1_2(1,2)).build()

    }

    //Dao
    single {
        get<AppDatabase>().getDao()
    }
    single {
        get<AppDatabase>().getStatesDao()
    }

    //RemoteDataSource
    single<CepRemoteDataSource>{
        CepRemoteDataSourceImpl(api = get<CepAPI>())
    }

    //LocalDataSource
    single<CepLocalDataSource>{
        CepLocalDataSourceImpl(dao = get<CepDao>())
    }
    single<StatesLocalDataSource>{
        StatesLocalDataSourceImpl(dao = get<StatesDao>())
    }

    //Repositories
    single <CepRepository>{
        CepRepositoryImpl(
            localDataSource = get<CepLocalDataSource>(),
            remoteDataSource = get<CepRemoteDataSource>()
        )
    }
    single <StatesRepository>{
        StatesRepositoryImpl(
            localDataSource = get<StatesLocalDataSource>()
        )
    }

    //UseCases
    factory { FavoriteCepUseCase(get<CepRepository>()) }
    factory { GetCepUseCase(get<CepRepository>()) }
    factory { GetDataFromCepUseCase(get<CepRepository>()) }
    factory { UnfavoriteCepUseCase(get<CepRepository>()) }
    factory { GetFavoriteDataUseCase(get<CepRepository>()) }
    factory { GetStatesUseCase(get<StatesRepository>()) }
    factory { InsertStatesUseCase(get<StatesRepository>()) }

    //ViewModel
    viewModel {
        MainViewModel(
            get<FavoriteCepUseCase>(),
            get<UnfavoriteCepUseCase>(),
            get<GetCepUseCase>(),
            get<GetDataFromCepUseCase>(),
            get<GetFavoriteDataUseCase>(),
            get(), get()
        )
    }
}
