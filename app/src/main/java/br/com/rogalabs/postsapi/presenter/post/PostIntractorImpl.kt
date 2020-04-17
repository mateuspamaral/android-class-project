package br.com.rogalabs.postsapi.presenter.post

import android.util.Log

import br.com.rogalabs.postsapi.model.Post
import br.com.rogalabs.postsapi.data.network.PostService
import br.com.rogalabs.postsapi.data.network.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PostIntractorImpl : PostContract.GetPostIntractor {
    override fun getPostArrayList(onFinishedListener: PostContract.GetPostIntractor.OnFinishedListener) {

        val dataService = RetrofitInstance.init.create(PostService::class.java)
        val postListCall = dataService.listPosts()
        val msg = postListCall.request().url().toString()
        Log.d("Retrofit: ", msg)

        postListCall.enqueue(object : Callback<List<Post>> {
            override fun onResponse(call: Call<List<Post>>, response: Response<List<Post>>) {
                val msg = response.body().toString()
                Log.d("Retrofit body: ", msg)

                response.body()?.let { onFinishedListener.onFinished(it) }

            }

            override fun onFailure(call: Call<List<Post>>, error: Throwable) {
                Log.e("Retrofit: ", "Something went wrong", error)
                onFinishedListener.onFailure(error)
            }
        })
    }
}