package com.n0stalgiaultra.database.utils

import com.n0stalgiaultra.database.entity.StatesLocal
import com.n0stalgiaultra.domain.entities.States

fun convertFromStateToLocal(data: States): StatesLocal{
    return StatesLocal(data.states)
}