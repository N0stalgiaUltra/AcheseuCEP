package com.n0stalgiaultra.domain.usecase.states

import com.n0stalgiaultra.domain.repository.StatesRepository

class GetStatesUseCase(private val repository: StatesRepository){

    suspend operator fun invoke(): List<String>{
        return repository.getStatesList()
    }
}