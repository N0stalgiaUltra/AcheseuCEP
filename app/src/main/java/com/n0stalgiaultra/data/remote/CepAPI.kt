package com.n0stalgiaultra.data.remote

import retrofit2.Call
import retrofit2.http.GET

interface CepAPI {
    /*TODO: Adicionar chamada com param*/
    @GET("RJ/Rio%20De%20Janeiro/Amaral/json/")
    fun getData() : Call<List<CepDto>>

//    @GET(getString)
//    fun getData(state: String, city: String, street: String){
//        val getString = "${state}/${city}/${street}/json/"
//    }
    companion object{
        const val BASE_URL = "https://viacep.com.br/ws/"
    }
}

//https://viacep.com.br/ws/RJ/Rio%20De%20Janeiro/Amaral/json/