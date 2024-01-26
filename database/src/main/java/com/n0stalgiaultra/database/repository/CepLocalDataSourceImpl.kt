package com.n0stalgiaultra.database.repository

import android.util.Log
import com.n0stalgiaultra.database.dao.CepDao
import com.n0stalgiaultra.database.entity.CepLocal
import com.n0stalgiaultra.database.entity.CepLocal.Companion.mapLocalToCep
import com.n0stalgiaultra.domain.model.CepDto
import com.n0stalgiaultra.database.utils.EmptyDataException
import com.n0stalgiaultra.database.utils.checkEmptyData
import com.n0stalgiaultra.database.utils.convertToLocal
import com.n0stalgiaultra.domain.CepLocalDataSource
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


    //se o id do remove for null, é pq é item dto.
    //se não, é pq é item local
    override fun remove(item: Cep) {
        val itemId = item.id

        if(itemId != null) {
            CoroutineScope(Dispatchers.IO).launch {
                val localCep = dao.getCard(itemId)
                dao.deleteCard(localCep.id)
            }
        }
        else{
            try{
                if(checkEmptyData(item)) {
                    CoroutineScope(Dispatchers.IO).launch {
                        val localCep = dao.getCard(convertToLocal(item).id)
                        dao.deleteCard(localCep.id)
                    }
                }
            }
            catch (e: EmptyDataException){
                Log.e("Error", e.message ?: "an error occurred")
            }
        }
    }

    override fun getItems(): List<Cep> {
        val returnList = dao.getAllCards()
        return returnList.map { mapLocalToCep(it)}
    }

}