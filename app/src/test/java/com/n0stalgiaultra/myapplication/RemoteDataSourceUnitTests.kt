package com.n0stalgiaultra.myapplication

import com.google.common.truth.Truth
import com.n0stalgiaultra.data.remote.CepAPI
import com.n0stalgiaultra.data.remote.CepDto
import dev.thiagosouto.butler.file.readFile
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class RemoteDataSourceUnitTests {
    @Test
    fun `should hit endpoints with expected parameters`() = runBlocking{
        val server = MockWebServer()
        server.start()

        val classLoader = javaClass.classLoader
        val resourcePath = "CEP.json"


        server.enqueue(MockResponse().setBody(readFile(resourcePath)))

        val retrofit = Retrofit.Builder()
            .baseUrl(server.url(""))
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service: com.n0stalgiaultra.data.remote.CepAPI = retrofit.create(com.n0stalgiaultra.data.remote.CepAPI::class.java)
        val result = service.getData("RJ", "Rio de Janeiro", "Rua Silva Teles")

        // Sempre fazer o "shutdown" do server
        val request = server.takeRequest()
        server.shutdown()

        Truth.assertThat(request.path)
            .isEqualTo("RJ/Rio%20De%20Janeiro/Rua%20Silva%20Teles/json/")
        Truth.assertThat(result).isEqualTo(
            listOf<com.n0stalgiaultra.data.remote.CepDto>(
                com.n0stalgiaultra.data.remote.CepDto(
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
                )
            )
        )
    }
}