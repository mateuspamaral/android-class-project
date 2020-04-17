package br.com.rogalabs.postsapi.data.network

import br.com.rogalabs.postsapi.model.Comment
import br.com.rogalabs.postsapi.model.Post
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface PostService {

    @GET("posts")
    fun listPosts(): Call<List<Post>>

    @GET("posts/{post_id}/comments")
    fun postId(@Path("post_id") post_id : Int ) : Call<List<Comment>>
}