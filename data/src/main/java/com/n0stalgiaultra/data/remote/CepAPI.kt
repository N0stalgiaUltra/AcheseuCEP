package com.n0stalgiaultra.data.remote

import com.n0stalgiaultra.domain.model.CepDto
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface CepAPI {
    @GET("{CEP}/json/")
    fun getData(@Path("CEP") CEP: String) : Call<CepDto>

    @GET("{estado}/{cidade}/{rua}/json/")
    fun getData(
        @Path("estado") estado: String,
        @Path("cidade") cidade: String,
        @Path("rua") rua: String): Call<List<CepDto>>

    companion object{
        const val BASE_URL = "https://viacep.com.br/ws/"
    }
}

//https://viacep.com.br/ws/RJ/Rio%20De%20Janeiro/Amaral/json/
//