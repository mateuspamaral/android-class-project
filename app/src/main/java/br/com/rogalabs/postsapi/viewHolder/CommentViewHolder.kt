package br.com.rogalabs.postsapi.viewHolder

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.rogalabs.postsapi.model.CommentModel
import com.manoelh.postsapiandroid.R

class CommentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val name = itemView.findViewById<TextView>(R.id.nameTextView)
    private val email = itemView.findViewById<TextView>(R.id.emailTextView)
    private val commentBody = itemView.findViewById<TextView>(R.id.bodyEditTextComment)

    fun bindTask(comment: CommentModel){
        name.text = comment.name
        email.text = comment.email
        commentBody.text = comment.body
    }
}