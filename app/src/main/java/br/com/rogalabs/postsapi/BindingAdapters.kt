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

@BindingAdapter("listPost")
fun bindRecyclerViewPost(recyclerView: RecyclerView, data: List<Post>?) {
    val adapter = recyclerView.adapter as PostListAdapter
    adapter.submitList(data)
}

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

@BindingAdapter("listComment")
fun bindRecyclerViewComment(recyclerView: RecyclerView, data: List<Comment>?) {
    val adapter = recyclerView.adapter as CommentAdapter
    adapter.submitList(data)
}

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