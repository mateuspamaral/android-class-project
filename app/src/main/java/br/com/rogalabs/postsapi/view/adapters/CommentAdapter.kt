package br.com.rogalabs.postsapi.view.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import br.com.rogalabs.postsapi.R
import br.com.rogalabs.postsapi.model.Comment
import kotlinx.android.synthetic.main.item_comment.view.*


class CommentAdapter(
        private val comments: ArrayList<Comment>,
        private val callback: (Comment) -> Unit) :
        RecyclerView.Adapter<CommentAdapter.VH>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val v = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_comment, parent, false)
        val vh = VH(v)
        vh.itemView.setOnClickListener {
            val message = comments[vh.adapterPosition]
            callback(message)
        }
        return vh
    }

    fun updateList(newlist: List<Comment>) {
        comments.clear()
        comments.addAll(newlist)
        this.notifyDataSetChanged()
    }
    override fun onBindViewHolder(holder: VH, pos: Int) {
        val (name, email,body) = comments[pos]
        holder.tvName.text = name
        holder.tvEmail.text = email
        holder.tvBody.text = body
    }
    override fun getItemCount(): Int = comments.size

    class VH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvName: TextView = itemView.tv_name_comment
        val tvEmail: TextView = itemView.tv_email_comment
        var tvBody: TextView = itemView.tv_body_comment
    }
}