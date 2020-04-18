package br.com.rogalabs.postsapi.service

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.google.gson.JsonArray
import com.google.gson.JsonParser
import com.google.gson.JsonSyntaxException
import com.google.gson.reflect.TypeToken
import br.com.rogalabs.postsapi.model.PostModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.reflect.Type

class ServiceRequest {

    private val TAG = "ServiceRequest"

    fun searchPostsFromAPI(): MutableLiveData<List<PostModel>> {

        val posts: MutableLiveData<List<PostModel>> = MutableLiveData()

        val serviceAPI: PostService? = ClientService.getClient()
        val loadTeamCall: Call<JsonArray?>? = serviceAPI?.readPostArray()
        var postsArray: MutableList<PostModel> ?
        loadTeamCall?.enqueue(object : Callback<JsonArray?> {
            override fun onResponse(
                call: Call<JsonArray?>?, response: Response<JsonArray?>) {
                try {
                    val teamString: String = response.body().toString()
                    val listType: Type =
                        object : TypeToken<List<PostModel?>?>() {}.type
                    postsArray = getTeamListFromJson(teamString, listType)
                    posts.postValue(postsArray)
                } catch (e: Exception) {
                    Log.d(TAG, "There is an error")
                    e.printStackTrace()
                }
            }

            override fun onFailure(call: Call<JsonArray?>?, t: Throwable) {
                Log.d("onFailure", t.toString())
                posts.postValue(null)
            }
        })
        return posts
    }

    private fun <T> getTeamListFromJson(jsonString: String?, type: Type?): MutableList<T>? {
        return if (!isValid(jsonString)) {
            null
        } else Gson().fromJson(jsonString, type)
    }

    private fun isValid(json: String?): Boolean {
        return try {
            JsonParser().parse(json)
            true
        } catch (jse: JsonSyntaxException) {
            false
        }
    }


//    fun searchPostsFromAPI(callback: (MutableList<PostModel>?) -> Unit) {
//        val service: PostRetrofitService = ServiceGenerator.createService(PostRetrofitService::class.java, RequestObject.POST)
//        val postModel = PostModel()
//        val call: Call<MutableList<PostModel>?>? = service.unityConverter(postModel.userId, postModel.id, postModel.title, postModel.body)
//        call?.enqueue(object : Callback<MutableList<PostModel>?> {
//            override fun onResponse(call: Call<MutableList<PostModel>?>?, response: Response<MutableList<PostModel>?>) {
//                if (response.isSuccessful) {
//                    val serviceResponse: MutableList<PostModel>? = response.body()
//                    callback(serviceResponse)
//                }
//
//                else {
//                    val errorBody: ResponseBody = response.errorBody()
//                    Log.e(TAG, errorBody.toString())
//                    callback(null)
//                }
//            }
//
//            override fun onFailure(call: Call<MutableList<PostModel>?>?, t: Throwable?) {
//                Log.e(TAG, t?.message)
//                callback(null)
//            }
//        })
//    }

}