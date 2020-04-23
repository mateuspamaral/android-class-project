package br.com.rogalabs.postsapi.service

import com.google.gson.JsonArray
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST


interface PostService {
    @GET("posts")
    fun readPostArray(): Call<JsonArray?>?
}