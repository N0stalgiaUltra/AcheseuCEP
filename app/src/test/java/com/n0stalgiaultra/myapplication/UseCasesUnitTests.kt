package com.n0stalgiaultra.myapplication

import com.n0stalgiaultra.data.local.CepDao
import com.n0stalgiaultra.data.local.CepLocal
import com.n0stalgiaultra.data.local.CepLocalDataSourceImpl
import com.n0stalgiaultra.data.remote.CepDto
import com.n0stalgiaultra.domain.CepLocalDataSource
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class UseCasesUnitTests {

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

        private fun convertToDto(item: CepLocal): CepDto{
            return CepDto(
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
}

/*
*
*
* */