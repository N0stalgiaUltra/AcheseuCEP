package com.n0stalgiaultra.myapplication

import android.test.mock.MockContext
import com.n0stalgiaultra.data.local.CepDao
import com.n0stalgiaultra.data.local.CepLocal
import com.n0stalgiaultra.data.local.CepLocalDataSourceImpl
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.koin.test.inject
import org.mockito.Mock
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

class DatabaseTesting: KoinTest {
    private val dataSource: CepLocalDataSourceImpl by inject()
//
//    @Mock
//    private val mockDao: CepDao = Mock(CepDao)

    @Before
    fun setUp(){
        startKoin{
            androidContext(MockContext())
            modules(
                testDatabaseModule
            )
        }
    }

    @After
    fun tearDown(){
        stopKoin()
    }

//    @Test
//    fun testInsertItem(){
//
//        val cepItem = CepLocal(
//            cep = "20541-110",
//            logradouro = "Rua Silva Teles",
//            complemento = "",
//            bairro = "Andarai",
//            localidade = "Rio de Janeiro",
//            uf = "RJ",
//            ibge = "3304557",
//            gia = "",
//            ddd = "21",
//            siafi = "6001"
//        )
//
//        //dao.insertCard(cepItem)
//
//        //val retrievedItem = dao.getCard(cepItem.id)
//        assertNotNull(cepItem)
//        assertNotNull(retrievedItem)
//        assertEquals(cepItem, retrievedItem)
//
//
//    }

}
/*
* 1- Verificar se está adicinando ao DB
* 2- Se um objeto que consta no DB pode ser lido
* 3- Se é possivel remover um objeto do DB
* 4- Se é possivel consultar um objeto que não está presente no DB
*
* */

