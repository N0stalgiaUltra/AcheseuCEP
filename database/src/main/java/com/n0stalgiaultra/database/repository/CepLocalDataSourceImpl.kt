package com.n0stalgiaultra.database.repository

import android.util.Log
import com.n0stalgiaultra.database.dao.CepDao
import com.n0stalgiaultra.database.entity.CepLocal.Companion.mapLocalToCep
import com.n0stalgiaultra.database.utils.EmptyDataException
import com.n0stalgiaultra.database.utils.checkEmptyData
import com.n0stalgiaultra.database.utils.convertToLocal
import com.n0stalgiaultra.domain.datasources.CepLocalDataSource
import com.n0stalgiaultra.domain.mapper.Cep
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CepLocalDataSourceImpl(val dao: CepDao): CepLocalDataSource {

    override fun insert(item: Cep) {
        try {
            if(checkEmptyData(item)) {
                CoroutineScope(Dispatchers.IO).launch {
                    dao.insertCard(convertToLocal(item))
                }
            }
            else{
                throw EmptyDataException("CANNOT ADD ITEM BECAUSE OF EMPTY PROPERTIES")
            }
        }
        catch (e: EmptyDataException){
            Log.e("Error", e.message?: "an error occurred")
        }
    }
    //ele tá chamando o Remove mais de uma vez

    // item ja adicionado local
    // recupera dado remoto
    // se tiver favoritado, consegue remover facil
    // senão, quebra com nullref
    override fun remove(item: Cep) {
        CoroutineScope(Dispatchers.IO).launch {
            val localCep = dao.getCard(item.cep)
            dao.deleteCard(localCep.cep)
        }
    }

    override fun getItems(): List<Cep> {
        val returnList = dao.getAllCards()
        return returnList.map { mapLocalToCep(it)}
    }

}