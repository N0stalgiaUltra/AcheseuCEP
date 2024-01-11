package com.n0stalgiaultra.data.local

import android.util.Log
import com.n0stalgiaultra.data.remote.CepDto
import com.n0stalgiaultra.domain.CepLocalDataSource
import com.n0stalgiaultra.utils.EmptyDataException
import com.n0stalgiaultra.utils.checkEmptyData
import com.n0stalgiaultra.utils.convertToLocal
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CepLocalDataSourceImpl(val dao: CepDao): CepLocalDataSource {

    override fun insert(item: CepDto) {
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

    override fun remove(item: CepDto) {
        CoroutineScope(Dispatchers.IO).launch {
            val localCep = dao.getCard(convertToLocal(item).id)
            dao.deleteCard(localCep.id)
        }
    }

    override fun remove(item: CepLocal) {
        CoroutineScope(Dispatchers.IO).launch {
            val localCep = dao.getCard(item.id)
            dao.deleteCard(localCep.id)
        }
    }

    override fun getItems(): List<CepLocal> {
        return dao.getAllCards()
    }

}