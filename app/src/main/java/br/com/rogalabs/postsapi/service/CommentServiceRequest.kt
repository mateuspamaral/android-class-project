package br.com.rogalabs.postsapi.service

import android.content.res.Resources
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.gson.JsonArray
import com.google.gson.reflect.TypeToken
import br.com.rogalabs.postsapi.model.CommentModel
import br.com.rogalabs.postsapi.util.JsonUtil
import com.manoelh.postsapiandroid.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.reflect.Type

class CommentServiceRequest {

    private val TAG = "CommentServiceRequest"

    fun searchCommentsFromAPI(postId: Int): MutableLiveData<List<CommentModel>> {

        val comments: MutableLiveData<List<CommentModel>> = MutableLiveData()

        val serviceAPI: CommentService? =
            ClientServiceComment.getClientComment(postId)
        val loadTeamCall: Call<JsonArray?>? = serviceAPI?.readPostArray()
        var commentsArray: MutableList<CommentModel> ?
        loadTeamCall?.enqueue(object : Callback<JsonArray?> {
            override fun onResponse(
                call: Call<JsonArray?>?, response: Response<JsonArray?>) {
                try {
                    val teamString: String = response.body().toString()
                    val listType: Type =
                        object : TypeToken<List<CommentModel?>?>() {}.type
                    commentsArray = JsonUtil.getListFromJson(teamString, listType)
                    comments.postValue(commentsArray)
                } catch (e: Exception) {
                    Log.d(TAG, Resources.getSystem().getString(R.string.error))
                    e.printStackTrace()
                }
            }

            override fun onFailure(call: Call<JsonArray?>?, t: Throwable) {
                Log.d(TAG, t.toString())
                comments.postValue(null)
            }
        })
        return comments
    }
}