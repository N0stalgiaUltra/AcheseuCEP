package com.n0stalgiaultra.data.remote

import android.util.Log
import com.n0stalgiaultra.domain.CepRemoteDataSource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class CepRemoteDataSourceImpl(private val api: CepAPI): CepRemoteDataSource {
    override suspend fun getRemoteCep(): List<CepDto> = suspendCoroutine {
        continuation ->
            val call: Call<List<CepDto>> = api.getData()
            call.enqueue(
                object: Callback<List<CepDto>>{
                    //                    override fun onResponse(call: Call<CepResponse>, response: Response<CepResponse>) {
//                        val cep = response.body()
//                        Log.d("remote response", cep.toString())
//                        if(cep != null){
//                            continuation.resume(
//                                cep.cepResponse.map { it.item }
//                            )
//                        }
//                        Log.d("remote response", "Success")
//                    }
//
//                    override fun onFailure(call: Call<CepResponse>, t: Throwable) {
//                        Log.e("remote response", t.message ?: "")
//                        continuation.resume(emptyList())
//                    }
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
}