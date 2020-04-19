package br.com.rogalabs.postsapi.data.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    private const val BASE_URL = "https://jsonplaceholder.typicode.com/"

    val init: Retrofit
        get() {
               val retrofit = Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build()

            return retrofit
        }
}
