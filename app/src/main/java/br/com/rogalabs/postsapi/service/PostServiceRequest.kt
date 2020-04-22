package br.com.rogalabs.postsapi.service

import android.content.res.Resources
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.gson.JsonArray
import com.google.gson.reflect.TypeToken
import br.com.rogalabs.postsapi.model.PostModel
import br.com.rogalabs.postsapi.util.JsonUtil
import com.manoelh.postsapiandroid.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.reflect.Type

class PostServiceRequest {

    private val TAG = "PostServiceRequest"

    fun searchPostsFromAPI(): MutableLiveData<List<PostModel>> {

        val posts: MutableLiveData<List<PostModel>> = MutableLiveData()

        val serviceAPI: PostService? =
            ClientServicePost.getClientPost()
        val loadTeamCall: Call<JsonArray?>? = serviceAPI?.readPostArray()
        var postsArray: MutableList<PostModel> ?
        loadTeamCall?.enqueue(object : Callback<JsonArray?> {
            override fun onResponse(
                call: Call<JsonArray?>?, response: Response<JsonArray?>) {
                try {
                    val teamString: String = response.body().toString()
                    val listType: Type =
                        object : TypeToken<List<PostModel?>?>() {}.type
                    postsArray = JsonUtil.getListFromJson(teamString, listType)
                    posts.postValue(postsArray)
                } catch (e: Exception) {
                    Log.d(TAG, Resources.getSystem().getString(R.string.error))
                    e.printStackTrace()
                }
            }

            override fun onFailure(call: Call<JsonArray?>?, t: Throwable) {
                Log.d(TAG, t.toString())
                posts.postValue(null)
            }
        })
        return posts
    }
}