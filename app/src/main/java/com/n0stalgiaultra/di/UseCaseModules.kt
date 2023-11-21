package com.n0stalgiaultra.di

import com.n0stalgiaultra.domain.CepRepository
import com.n0stalgiaultra.domain.usecase.FavoriteCepUseCase
import com.n0stalgiaultra.domain.usecase.GetCepUseCase
import com.n0stalgiaultra.domain.usecase.GetDataFromCepUseCase
import com.n0stalgiaultra.domain.usecase.GetFavoriteDataUseCase
import com.n0stalgiaultra.domain.usecase.UnfavoriteCepUseCase
import com.n0stalgiaultra.view.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCasesModule = module {
    factory {
        FavoriteCepUseCase(get<CepRepository>())
    }

    factory {
        GetCepUseCase(get<CepRepository>())
    }

    factory {
        GetDataFromCepUseCase(get<CepRepository>())
    }

    factory {
        UnfavoriteCepUseCase(get<CepRepository>())
    }

    factory {
        GetFavoriteDataUseCase(get<CepRepository>())
    }

}

val mainViewModelModule = module {
    viewModel {
        MainViewModel(
            get<FavoriteCepUseCase>(),
            get<UnfavoriteCepUseCase>(),
            get<GetCepUseCase>(),
            get<GetDataFromCepUseCase>(),
            get<GetFavoriteDataUseCase>(),

        )
    }
}
