package br.com.rogalabs.postsapi.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.manoelh.postsapiandroid.R
import br.com.rogalabs.postsapi.model.CommentModel
import br.com.rogalabs.postsapi.viewHolder.CommentViewHolder

class CommentListAdapter(private var commentList: MutableList<CommentModel>):
                      RecyclerView.Adapter<CommentViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder {
        val inflate = LayoutInflater.from(parent.context)
        val view = inflate.inflate(R.layout.comment_list , parent, false)
        return CommentViewHolder(view)
    }

    override fun getItemCount(): Int {
        return commentList.count()
    }

    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
        holder.bindTask(commentList[position])
    }

    fun setData(list: MutableList<CommentModel>){
        this.commentList = list
        this.notifyDataSetChanged()
    }
}