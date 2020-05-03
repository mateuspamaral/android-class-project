package br.com.rogalabs.postsapi

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import br.com.rogalabs.postsapi.comment.CommentAdapter
import br.com.rogalabs.postsapi.comment.CommentApiStatus
import br.com.rogalabs.postsapi.network.Comment
import br.com.rogalabs.postsapi.network.Post
import br.com.rogalabs.postsapi.post.PostApiStatus
import br.com.rogalabs.postsapi.post.PostListAdapter

/**
 * When there is no Post data (data is null), hide the [RecyclerView], otherwise show it.
 */
@BindingAdapter("listPost")
fun bindRecyclerViewPost(recyclerView: RecyclerView, data: List<Post>?) {
    val adapter = recyclerView.adapter as PostListAdapter
    adapter.submitList(data)
}

/**
 * This binding adapter displays the [PostApiStatus] of the network request in an image view.  When
 * the request is loading, it displays a loading_animation.  If the request has an error, it
 * displays a broken image to reflect the connection error.  When the request is finished, it
 * hides the image view.
 */
@BindingAdapter("postApiStatus")
fun bindStatusPost(statusImageView: ImageView, status: PostApiStatus?) {
    when (status) {
        PostApiStatus.LOADING -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.loading_animation)
        }
        PostApiStatus.ERROR -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.ic_connection_error)
        }
        PostApiStatus.DONE -> {
            statusImageView.visibility = View.GONE
        }
    }
}

/**
 * When there is no Comment data (data is null), hide the [RecyclerView], otherwise show it.
 */
@BindingAdapter("listComment")
fun bindRecyclerViewComment(recyclerView: RecyclerView, data: List<Comment>?) {
    val adapter = recyclerView.adapter as CommentAdapter
    adapter.submitList(data)
}

/**
 * This binding adapter displays the [CommentApiStatus] of the network request in an image view.  When
 * the request is loading, it displays a loading_animation.  If the request has an error, it
 * displays a broken image to reflect the connection error.  When the request is finished, it
 * hides the image view.
 */
@BindingAdapter("commentApiStatus")
fun bindStatusComment(statusImageView: ImageView, status: CommentApiStatus?) {
    when (status) {
        CommentApiStatus.LOADING -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.loading_animation)
        }
        CommentApiStatus.ERROR -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.ic_connection_error)
        }
        CommentApiStatus.DONE -> {
            statusImageView.visibility = View.GONE
        }
    }
}