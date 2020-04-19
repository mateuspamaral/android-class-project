package br.com.rogalabs.postsapi.view.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import br.com.rogalabs.postsapi.R
import br.com.rogalabs.postsapi.model.Post
import kotlinx.android.synthetic.main.item_post.view.*

class PostAdapter (
        private val posts: ArrayList<Post>,
        private val callback: (Post) -> Unit) :
        RecyclerView.Adapter<PostAdapter.VH>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val v = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_post, parent, false)
        val vh = VH(v)
        vh.itemView.setOnClickListener {
            val message = posts[vh.adapterPosition]
            callback(message)
        }
        return vh
    }

    fun updateList(newlist: List<Post>) {
        posts.clear()
        posts.addAll(newlist)
        this.notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: VH, pos: Int) {
        val (title, body) = posts[pos]
        holder.tvTitle.text = title
        holder.tvBody.text = body
    }

    override fun getItemCount(): Int = posts.size

    class VH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvTitle: TextView = itemView.tv_title_post
        var tvBody: TextView = itemView.tv_body_post
    }
}