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

/**
 *  The [ViewModel] associated with the [CommentFragment], containing information about the selected
 *  [Posts].
 */

enum class CommentApiStatus { LOADING, ERROR, DONE }

class CommentViewModel(post: Post, app: Application) : AndroidViewModel(app) {
    // The internal MutableLiveData that stores the status of the most recent request
    private val _selectedPost = MutableLiveData<Post>()

    // The external LiveData for the SelectedPost
    private val selectedPost: LiveData<Post>
        get() = _selectedPost

    // The internal MutableLiveData that stores the status of the most recent request
    private val _status = MutableLiveData<CommentApiStatus>()
    // The external immutable LiveData for the request status
    val status: LiveData<CommentApiStatus>
        get() = _status

    // Internally, we use a MutableLiveData, because we will be updating the List of Comments
    // with new values
    private val _comments = MutableLiveData<List<Comment>>()
    // The external LiveData interface to the comment is immutable, so only this class can modify
    val comments: LiveData<List<Comment>>
        get() = _comments

    // Create a Coroutine scope using a job to be able to cancel when needed
    private var viewModelJob = Job()
    // the Coroutine runs using the Main (UI) dispatcher
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    /**
     * Call getComments() on init so we can display status immediately.
     * Initialize the _selectedProperty MutableLiveData
     */
    init {
        _selectedPost.value = post
        getComments()
    }

    /**
     * The displayPostTitle formatted Transformation Map LiveData, which displays
     * the title of [CommentFragment]
     */
    val displayPostTitle = Transformations.map(selectedPost) {
        app.applicationContext.getString(R.string.comment_post_title, it.title)
    }

    /**
     * Gets comments information from the Mars API Retrofit service and
     * updates the [Comment] [List] and [CommentApiStatus] [LiveData]. The Retrofit service
     * returns a coroutine Deferred, which we await to get the result of the transaction.
     * The selected post id is sent as part of the web server request
     */
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

    /**
     * When the [ViewModel] is finished, we cancel our coroutine [viewModelJob], which tells the
     * Retrofit service to stop.
     */
    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}