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

class PostsViewModel : ViewModel() {

    // Variável interna que guarda o valor da requisição mais atual
    private val _status = MutableLiveData<PostApiStatus>()

    // Variável externa da requisição
    val status: LiveData<PostApiStatus>
        get() = _status

    // Pegando a lista de post externos que podem ser alterados
    private val _posts = MutableLiveData<List<Post>>()

    // Dados não alteráveis
    val post: LiveData<List<Post>>
        get() = _posts

    // Internamente vamos usar MutableLiveData para abrir o post selecionado
    private val _navigateToSelectedPost = MutableLiveData<Post>()

    // Os dados do post que não são mutáveis
    val navigateToSelectedPost: LiveData<Post>
        get() = _navigateToSelectedPost

    // Criado uma Coroutine para que possa ser cancelado posteriormente
    private var viewModelJob = Job()

    // Criado escopo da Coroutine e apontado para o dispatcher principal
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    // Chamado getPosts aqui para obter o status assim que o ViewModel for iniciado
    init {
        getPosts()
    }

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

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    fun displayPostDetails(post: Post) {
        _navigateToSelectedPost.value = post
    }

    fun displayPostDetailsComplete() {
        _navigateToSelectedPost.value = null
    }
}