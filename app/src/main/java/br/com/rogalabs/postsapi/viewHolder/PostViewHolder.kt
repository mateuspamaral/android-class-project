package br.com.rogalabs.postsapi.viewHolder

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.manoelh.postsapiandroid.R
import br.com.rogalabs.postsapi.interfaces.OnPostListInteractionListener
import br.com.rogalabs.postsapi.model.PostModel

class PostViewHolder(itemView: View,
                     private val onPostListInteractionListener: OnPostListInteractionListener)
                    : RecyclerView.ViewHolder(itemView) {

    private val postTitle = itemView.findViewById<TextView>(R.id.titleTextView)
    private val postBody = itemView.findViewById<TextView>(R.id.bodyEditTextPost)

    fun bindTask(post: PostModel){
        postTitle.text = post.title
        postBody.text = post.body
        postTitle.setOnClickListener {
            onPostListInteractionListener.onListClick(post)
        }
        postBody.setOnClickListener {
            onPostListInteractionListener.onListClick(post)
        }
    }
}