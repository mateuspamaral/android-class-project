package br.com.rogalabs.postsapi.post

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.rogalabs.postsapi.network.Post
import br.com.rogalabs.postsapi.network.PostApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.lang.Exception

enum class PostApiStatus { LOADING, ERROR, DONE }

/**
 * The [ViewModel] that is attached to the [PostListFragment].
 */
class PostsViewModel : ViewModel() {

    // The internal MutableLiveData that stores the status of the most recent request
    private val _status = MutableLiveData<PostApiStatus>()

    // The external immutable LiveData for the request status
    val status: LiveData<PostApiStatus>
        get() = _status

    // Internally, we use a MutableLiveData, because we will be updating the List of Post
    // with new values
    private val _posts = MutableLiveData<List<Post>>()

    // The external LiveData interface to the post is immutable, so only this class can modify
    val post: LiveData<List<Post>>
        get() = _posts

    // Internally, we use a MutableLiveData to handle navigation to the selected post
    private val _navigateToSelectedPost = MutableLiveData<Post>()

    // The external immutable LiveData for the navigation post
    val navigateToSelectedPost: LiveData<Post>
        get() = _navigateToSelectedPost

    // Create a Coroutine scope using a job to be able to cancel when needed
    private var viewModelJob = Job()

    // the Coroutine runs using the Main (UI) dispatcher
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    /**
     * Call getPosts() on init so we can display status immediately.
     */
    init {
        getPosts()
    }

    /**
     * Gets Post information from the Post API Retrofit service and
     * updates the [Post] [List] and [PostApiStatus] [LiveData]. The Retrofit service
     * returns a coroutine Deferred, which we await to get the result of the transaction.
     */
    private fun getPosts() {
        coroutineScope.launch {
            var getPostsDeferred = PostApi.retrofitService.getPostsAsync()
            try {
                _status.value = PostApiStatus.LOADING
                val listResult = getPostsDeferred.await()
                _status.value = PostApiStatus.DONE
                _posts.value = listResult
            } catch (e: Exception) {
                _status.value = PostApiStatus.ERROR
                _posts.value = ArrayList()
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

    /**
     * When the post is clicked, set the [_navigateToSelectedPost] [MutableLiveData]
     * @param post The [Post] that was clicked on.
     */
    fun displayPostDetails(post: Post) {
        _navigateToSelectedPost.value = post
    }

    /**
     * After the navigation has taken place, make sure _navigateToSelectedPost is set to null
     */
    fun displayPostDetailsComplete() {
        _navigateToSelectedPost.value = null
    }
}