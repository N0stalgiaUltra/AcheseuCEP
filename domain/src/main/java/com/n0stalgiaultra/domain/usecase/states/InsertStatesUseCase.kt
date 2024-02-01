package com.n0stalgiaultra.domain.usecase.states

import com.n0stalgiaultra.domain.repository.StatesRepository

class InsertStatesUseCase(private val repository: StatesRepository) {
    suspend operator fun invoke(){
        repository.insertStatesList()
    }
}