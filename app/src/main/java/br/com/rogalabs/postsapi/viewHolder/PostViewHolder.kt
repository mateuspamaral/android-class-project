package br.com.rogalabs.postsapi.viewHolder

import android.content.Context
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.manoelh.postsapiandroid.R
import br.com.rogalabs.postsapi.interfaces.OnPostListInteractionListener
import br.com.rogalabs.postsapi.model.PostModel

class PostViewHolder(itemView: View, private val context: Context,
                     private val onPostListInteractionListener: OnPostListInteractionListener
)
                    : RecyclerView.ViewHolder(itemView) {

    private val postTitle = itemView.findViewById<TextView>(R.id.titleTextViewMain)
    private val postBody = itemView.findViewById<TextView>(R.id.bodyEditText)

    fun bindTask(post: PostModel){
        postTitle.text = post.title
        postBody.text = post.body
        postTitle.setOnClickListener {
            onPostListInteractionListener.onListClick(post.id)
        }

//        postBody.setOnClickListener {
//            onPostListInteractionListener.onListClick(post.id)
//        }
    }
}