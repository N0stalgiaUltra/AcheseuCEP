package com.n0stalgiaultra.data.local

import com.n0stalgiaultra.data.remote.CepDto
import com.n0stalgiaultra.domain.CepLocalDataSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CepLocalDataSourceImpl(val dao: CepDao): CepLocalDataSource {

    override fun insert(item: CepDto) {
        CoroutineScope(Dispatchers.IO).launch {
            dao.insertCard(convertToLocal(item))
        }
    }

    override fun remove(item: CepDto) {
        CoroutineScope(Dispatchers.IO).launch {
            val localCep = dao.getCard(convertToLocal(item).id)
            if(localCep != null)
                dao.deleteCard(localCep.id)
        }
    }

    override fun getItems(): List<CepLocal> {
        return dao.getAllCards()
    }


    private fun convertToLocal(item: CepDto): CepLocal{
        return CepLocal(
                cep = item.cep,
                logradouro = item.logradouro,
                complemento = item.complemento,
                bairro = item.bairro,
                localidade = item.localidade,
                uf = item.uf,
                ibge = item.ibge,
                gia = item.gia,
                ddd = item.ddd,
                siafi = item.siafi)
    }

}