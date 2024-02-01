package com.n0stalgiaultra.domain.datasources

import com.n0stalgiaultra.domain.entities.States

interface StatesLocalDataSource {
    fun insert(data: States)
    fun getItems() : List<String>
}