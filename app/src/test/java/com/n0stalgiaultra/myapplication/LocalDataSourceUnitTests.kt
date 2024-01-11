package com.n0stalgiaultra.myapplication

import com.n0stalgiaultra.data.local.CepDao
import com.n0stalgiaultra.data.local.CepLocal
import com.n0stalgiaultra.data.remote.CepDto
import com.n0stalgiaultra.domain.CepLocalDataSource
import com.n0stalgiaultra.utils.checkEmptyData
import com.n0stalgiaultra.utils.convertToDto
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers.any
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.never
import org.mockito.MockitoAnnotations

class LocalDataSourceUnitTests {

    @Mock
    lateinit var mockLocalDataSource: CepLocalDataSource

    @Mock
    lateinit var mockCepDao: CepDao

    @Before
    fun setup(){
        MockitoAnnotations.openMocks(this)
    }


    @Test
    fun `addCepToFavorite`(){
        //given
        Mockito.`when`(mockLocalDataSource.getItems())
            .thenReturn(
            mockFavoriteList
        )

        //when
        val item = CepLocal(
            cep = "20541-115",
            logradouro = "Teste3",
            complemento = "",
            bairro = "Andarai",
            localidade = "Rio de Janeiro",
            uf = "RJ",
            ibge = "3304560",
            gia = "",
            ddd = "21",
            siafi = "6010"
        )
        val newMockList = mutableListOf(item)
        newMockList.addAll(mockLocalDataSource.getItems())

        Mockito.`when`(mockLocalDataSource.getItems()).thenReturn(newMockList)

        runBlocking {
            mockLocalDataSource.insert(convertToDto(item))
        }

        //then
        Mockito.verify(mockLocalDataSource).insert(convertToDto(item))
        Assert.assertTrue(newMockList.contains(item))
    }

    @Test
    fun `cannotAddCepToFavoriteWithMissingInfo`(){
        //given
        Mockito.`when`(mockLocalDataSource.getItems()).thenReturn(
            mockFavoriteList
        )

        //when
        val item = CepLocal(
            cep = "",
            logradouro = "",
            complemento = "",
            bairro = "",
            localidade = "",
            uf = "",
            ibge = "",
            gia = "",
            ddd = "",
            siafi = ""
        )


        runBlocking {
            if(checkEmptyData(item))
                mockLocalDataSource.insert(convertToDto(item))
        }

        //never garante que o metodo 'insert' nunca foi chamado
        Mockito.verify(mockLocalDataSource, never()).insert(convertToDto(item))
    }

//    @Test
//    fun ``(){
//
//    }
    /* Favorite Mock List */
    companion object{
        val mockFavoriteList = listOf<CepLocal>(
            CepLocal(
            cep = "20541-110",
            logradouro = "Rua Silva Teles",
            complemento = "",
            bairro = "Andarai",
            localidade = "Rio de Janeiro",
            uf = "RJ",
            ibge = "3304557",
            gia = "",
            ddd = "21",
            siafi = "6001"
        ),CepLocal(
            cep = "20541-111",
            logradouro = "Rua Teste 1",
            complemento = "",
            bairro = "Andarai",
            localidade = "Rio de Janeiro",
            uf = "RJ",
            ibge = "3304558",
            gia = "",
            ddd = "21",
            siafi = "6002"
        ),CepLocal(
            cep = "20541-112",
            logradouro = "Rua Teste 2 ",
            complemento = "",
            bairro = "Andarai",
            localidade = "Rio de Janeiro",
            uf = "RJ",
            ibge = "3304559",
            gia = "",
            ddd = "21",
            siafi = "6003"
        ),
        )
    }
}
