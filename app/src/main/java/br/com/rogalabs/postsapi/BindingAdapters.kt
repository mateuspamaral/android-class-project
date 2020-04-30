package br.com.rogalabs.postsapi

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import br.com.rogalabs.postsapi.network.Post
import br.com.rogalabs.postsapi.post.PostApiStatus
import br.com.rogalabs.postsapi.post.PostListAdapter

@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<Post>?) {
    val adapter = recyclerView.adapter as PostListAdapter
    adapter.submitList(data)
}

@BindingAdapter("postsApiStatus")
fun bindStatus(statusImageView: ImageView, status: PostApiStatus?) {
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