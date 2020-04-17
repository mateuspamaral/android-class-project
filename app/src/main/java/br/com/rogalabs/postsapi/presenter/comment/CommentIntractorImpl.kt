package br.com.rogalabs.postsapi.presenter.comment

import android.util.Log

import br.com.rogalabs.postsapi.data.network.PostService
import br.com.rogalabs.postsapi.data.network.RetrofitInstance
import br.com.rogalabs.postsapi.model.Comment
import br.com.rogalabs.postsapi.model.Post
import br.com.rogalabs.postsapi.presenter.post.PostContract
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

internal class CommentIntractorImpl : CommentContract.GetCommentIntractor {


    override fun getCommentArrayList(id: Int, onFinishedListener: CommentContract.GetCommentIntractor.OnFinishedListener) {

        val dataService = RetrofitInstance.init.create(PostService::class.java)
        val commentListCall = dataService.postId(id)
        val msg = commentListCall.request().url().toString()
        Log.d("Retrofit: ", msg)

        commentListCall.enqueue(object : Callback<List<Comment>> {
            override fun onResponse(call: Call<List<Comment>>, response: Response<List<Comment>>) {
                val msg = response.body().toString()
                Log.d("Retrofit body: ", msg)
                response.body()?.let {
                    onFinishedListener.onFinished(it)
                }
            }

            override fun onFailure(call: Call<List<Comment>>, error: Throwable) {
                Log.e("Retrofit: ", "Something went wrong", error)
                onFinishedListener.onFailure(error)
            }
        })
    }
}