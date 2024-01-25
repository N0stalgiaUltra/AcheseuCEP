package com.n0stalgiaultra.domain.usecase

import com.n0stalgiaultra.data.local.CepLocal
import com.n0stalgiaultra.data.remote.CepDto
import com.n0stalgiaultra.domain.CepRepository

class UnfavoriteCepUseCase (private val repository: CepRepository) {
    suspend operator fun invoke(item: Any){
        when(item){
            is com.n0stalgiaultra.data.remote.CepDto -> {
                repository.removeLocalData(item)
            }
            is CepLocal -> {
                repository.removeLocalData(item)
            }
        }
    }
}