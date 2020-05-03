package br.com.rogalabs.postsapi.comment

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.com.rogalabs.postsapi.network.Post

/**
 * Simple ViewModel factory that provides the MarsProperty and context to the ViewModel.
 */

class CommentViewModelFactory(
    private val post: Post,
    private val application: Application) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CommentViewModel::class.java)) {
            return CommentViewModel(post, application) as T
        }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
}