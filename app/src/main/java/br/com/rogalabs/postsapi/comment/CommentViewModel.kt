package br.com.rogalabs.postsapi.comment

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import br.com.rogalabs.postsapi.R
import br.com.rogalabs.postsapi.network.Comment
import br.com.rogalabs.postsapi.network.CommentApi
import br.com.rogalabs.postsapi.network.Post
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

enum class CommentApiStatus { LOADING, ERROR, DONE }

class CommentViewModel(post: Post, app: Application) : AndroidViewModel(app) {
    private val _selectedPost = MutableLiveData<Post>()

    private val selectedPost: LiveData<Post>
        get() = _selectedPost

    private val _status = MutableLiveData<CommentApiStatus>()
    val status: LiveData<CommentApiStatus>
        get() = _status

    private val _comments = MutableLiveData<List<Comment>>()
    val comments: LiveData<List<Comment>>
        get() = _comments

    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    init {
        _selectedPost.value = post
        getComments()
    }

    val displayPostTitle = Transformations.map(selectedPost) {
        app.applicationContext.getString(R.string.comment_post_title, it.title)
    }

    private fun getComments() {
        coroutineScope.launch {
            var getCommentsDeferred = CommentApi.retrofitService.getCommentsAsync(_selectedPost.value!!.id)
            try {
                _status.value = CommentApiStatus.LOADING
                val listResult = getCommentsDeferred.await()
                _status.value = CommentApiStatus.DONE
                _comments.value = listResult
            } catch (e: Exception) {
                _status.value = CommentApiStatus.ERROR
                _comments.value = ArrayList()
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}