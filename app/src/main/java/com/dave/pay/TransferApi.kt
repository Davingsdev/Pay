package com.dave.pay

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface TransferApi {

    @Headers("Content-Type: application/json")
    @POST("transfers")
    fun createTransfer(@Body transfer: Transfer) : Call<Transfer>

}