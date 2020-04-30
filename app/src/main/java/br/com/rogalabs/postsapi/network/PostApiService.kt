package br.com.rogalabs.postsapi.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Deferred
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://jsonplaceholder.typicode.com/"

/**
 * Moshi é uma biblioteca JSON que facilita o parce de JSON para Java/Kotlin
 */
private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

/**
 * Criando um objeto Retrofit utilizando o objeto criado com o Moshi
 */
private val retrofit = Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .baseUrl(BASE_URL)
        .build()

interface PostApiService {
    /**
     * Retorna uma Coroutine, assim podemos pegar apenas quando retornar
     * algum dado, deixando a UI livre de travamentos
     */
    @GET("posts")
    fun getPostsAsync():
            Deferred<List<Post>>
}

object PostApi {
    val retrofitService : PostApiService by lazy { retrofit.create(PostApiService::class.java) }
}