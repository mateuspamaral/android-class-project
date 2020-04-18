package br.com.rogalabs.postsapi.service

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class ClientService {

    companion object{
        private var postService: PostService? = null
        fun getClient(): PostService? {
            if (postService == null) {
                val gson = GsonBuilder()
                    .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                    .create()
                val retrofit = Retrofit.Builder()
                    .baseUrl("https://jsonplaceholder.typicode.com")
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build()
                postService = retrofit.create<PostService>(PostService::class.java)
            }
            return postService
        }
    }
}