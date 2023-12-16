package com.example.weatherapp.data.network

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.Exception

class NetworkService private constructor() {
    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val weatherApi: WeatherApi
        get() = retrofit.create(WeatherApi::class.java)

    companion object {

        @Volatile
        private var instance: NetworkService? = null

        fun getInstance() =
            instance ?: synchronized(this) {
                instance ?: NetworkService().also { instance = it }
            }

        private const val BASE_URL = "https://api.weatherapi.com/v1/"

        suspend fun <T> handleCall(call: Call<T>): T? = withContext(Dispatchers.Default) {
            return@withContext try {
                val response = call.execute()
                if (response.isSuccessful) {
                    return@withContext response.body()
                } else {
                    throw IllegalArgumentException("Api returns nothing")
                }
            } catch (exception: Exception) {
                val message = "Error executing the request: ${exception.localizedMessage}"
                Log.e("NetworkService", message)
                null
            }
        }
    }
}
