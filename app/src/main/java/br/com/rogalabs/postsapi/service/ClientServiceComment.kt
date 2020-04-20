package br.com.rogalabs.postsapi.service

import br.com.rogalabs.postsapi.constants.WebServiceConstants
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class ClientServiceComment {

    companion object{
        private var commentService: CommentService? = null
        private val barra = "/"
        fun getClientComment(postId: Int): CommentService? {
            if (commentService == null) {
                val gson = GsonBuilder()
                    .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                    .create()
                val retrofit = Retrofit.Builder()
                    .baseUrl(WebServiceConstants.BASE_URL_COMMENTS+postId+barra)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build()
                commentService = retrofit.create<CommentService>(CommentService::class.java)
            }
            return commentService
        }
    }
}