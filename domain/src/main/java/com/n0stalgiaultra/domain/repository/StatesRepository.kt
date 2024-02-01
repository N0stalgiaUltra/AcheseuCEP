package com.n0stalgiaultra.domain.repository

interface StatesRepository {

    suspend fun insertStatesList()

    suspend fun getStatesList(): List<String>
}