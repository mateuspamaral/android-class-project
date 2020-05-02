package br.com.rogalabs.postsapi.comment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import br.com.rogalabs.postsapi.databinding.ListItemCommentBinding
import br.com.rogalabs.postsapi.network.Comment

class CommentAdapter() :
        ListAdapter<Comment, CommentAdapter.CommentViewHolder>(DiffCallback) {

    class CommentViewHolder(private var binding: ListItemCommentBinding):
            RecyclerView.ViewHolder(binding.root) {
        fun bind(comment: Comment) {
            binding.commentVariable = comment
            binding.executePendingBindings()
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<Comment>() {
        override fun areItemsTheSame(oldItem: Comment, newItem: Comment): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Comment, newItem: Comment): Boolean {
            return oldItem.id == newItem.id
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder {
        return CommentViewHolder(ListItemCommentBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
        val post = getItem(position)
        holder.bind(post)
    }
}