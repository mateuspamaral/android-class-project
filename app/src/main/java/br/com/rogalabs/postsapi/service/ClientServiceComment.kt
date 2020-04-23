package br.com.rogalabs.postsapi.service

import br.com.rogalabs.postsapi.constants.WebServiceConstants.Companion.BARRA
import br.com.rogalabs.postsapi.constants.WebServiceConstants.Companion.BASE_URL_COMMENTS
import br.com.rogalabs.postsapi.constants.WebServiceConstants.Companion.DATE_FORMAT
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class ClientServiceComment {

    companion object{
        private var commentService: CommentService? = null
        fun getClientComment(postId: Int): CommentService? {
            if (commentService == null) {
                val gson = GsonBuilder()
                    .setDateFormat(DATE_FORMAT)
                    .create()
                val retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL_COMMENTS+postId+BARRA)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build()
                commentService = retrofit.create<CommentService>(CommentService::class.java)
            }
            return commentService
        }
    }
}