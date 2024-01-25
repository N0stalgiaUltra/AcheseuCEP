package com.n0stalgiaultra.data.repository

import android.util.Log
import com.n0stalgiaultra.data.remote.CepAPI
import com.n0stalgiaultra.data.remote.CepDto
import com.n0stalgiaultra.domain.CepRemoteDataSource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class CepRemoteDataSourceImpl(private val api: CepAPI): CepRemoteDataSource {

    override suspend fun getRemoteCep(
        state: String,
        city: String,
        street: String): List<CepDto> = suspendCoroutine {
        continuation ->
            val call: Call<List<CepDto>> = api.getData(state, city, street)
            call.enqueue(
                object: Callback<List<CepDto>>{
                    override fun onResponse(
                        call: Call<List<CepDto>>,
                        response: Response<List<CepDto>>
                    ) {
                        val cep = response.body()
                        Log.d("remote response", cep.toString())
                        if(cep != null){
                            continuation.resume(
                                cep
                            )
                        }
                        Log.d("remote response", "Success")
                    }

                    override fun onFailure(call: Call<List<CepDto>>, t: Throwable) {
                        Log.e("remote response", t.message ?: "")
                        continuation.resume(emptyList())
                    }

                }
            )
        }

    override suspend fun getCepData(cep: String): CepDto = suspendCoroutine {
            continuation ->
            val call: Call<CepDto> = api.getData(cep)
            call.enqueue(
                object: Callback<CepDto>{
                    override fun onResponse(
                        call: Call<CepDto>,
                        response: Response<CepDto>
                    ) {
                        val cep = response.body()
                        Log.d("remote response", cep.toString())
                        if(cep != null){
                            continuation.resume(
                                cep
                            )
                        }
                        Log.d("remote response", "Success")
                    }

                    override fun onFailure(call: Call<CepDto>, t: Throwable) {
                        Log.e("remote response", t.message ?: "")
                    }

                }
            )
        }
}