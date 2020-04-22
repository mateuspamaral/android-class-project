package br.com.rogalabs.postsapi.service

import br.com.rogalabs.postsapi.constants.WebServiceConstants.Companion.BASE_URL_POST
import br.com.rogalabs.postsapi.constants.WebServiceConstants.Companion.DATE_FORMAT
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class ClientServicePost {

    companion object {
        private var postService: PostService? = null
        fun getClientPost(): PostService? {

            if (postService == null) {
                val gson = GsonBuilder()
                    .setDateFormat(DATE_FORMAT)
                    .create()
                val retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL_POST)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build()
                postService = retrofit.create<PostService>(PostService::class.java)
            }
            return postService
        }
    }
}