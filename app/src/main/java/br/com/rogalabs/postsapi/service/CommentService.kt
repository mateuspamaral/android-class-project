package br.com.rogalabs.postsapi.service

import com.google.gson.JsonArray
import retrofit2.Call
import retrofit2.http.GET

interface CommentService {
    @GET("comments")
    fun readPostArray(): Call<JsonArray?>?
}