package com.n0stalgiaultra.database.repository

import com.n0stalgiaultra.database.dao.StatesDao
import com.n0stalgiaultra.database.entity.StatesLocal
import com.n0stalgiaultra.database.utils.convertFromStateToLocal
import com.n0stalgiaultra.domain.datasources.StatesLocalDataSource
import com.n0stalgiaultra.domain.entities.States

class StatesLocalDataSourceImpl(private val dao: StatesDao): StatesLocalDataSource {

    override fun insert(data: States) {
        dao.insert(convertFromStateToLocal(data))
    }

    override fun getItems(): List<String> {
        return dao.getStates().map { it.states }
    }

}
