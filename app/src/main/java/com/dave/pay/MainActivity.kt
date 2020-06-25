package com.dave.pay

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private lateinit var transferApi: TransferApi
    private val bankInput = "044"
    private val numberInput = "0690000040"
    private val amountInput = 500
    private val currency = "NGN"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val secret = "FLWSECK_TEST-3550dd5e50b825327314d82e3cceccfb-X"

        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.flutterwave.com/v3/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(OkHttpClient.Builder().addInterceptor { chain ->
                val request = chain.request().newBuilder().addHeader("Authorization", "Bearer $secret").build()
                chain.proceed(request)
            }.build())
            .build()

        transferApi = retrofit.create(TransferApi::class.java)

        createTransfer()

    }

    private fun createTransfer(){

        val transferCode = Transfer(bankInput,numberInput,amountInput,currency)
        val call = transferApi.createTransfer(transferCode)

        call.enqueue(object : Callback<Transfer> {

            @SuppressLint("SetTextI18n")
            override fun onResponse(call: Call<Transfer>, response: Response<Transfer>) {
                if (!response.isSuccessful) {
                    responseText.text ="Code${response.code()}"
                    return
                }
                responseText.text = response.body().toString()
            }

            override fun onFailure(call: Call<Transfer>, t: Throwable) {
                responseText.text = (t.message)
            }

        })

    }

}