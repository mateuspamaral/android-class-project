package br.com.rogalabs.postsapi.post

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import br.com.rogalabs.postsapi.databinding.ListItemPostBinding
import br.com.rogalabs.postsapi.network.Post

class PostListAdapter(private val onClickListener: OnClickListener) :
        ListAdapter<Post, PostListAdapter.PostViewHolder>(DiffCallback) {

    class PostViewHolder(private var binding: ListItemPostBinding):
            RecyclerView.ViewHolder(binding.root) {
        fun bind(post: Post) {
            binding.postVariable = post
            binding.executePendingBindings()
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<Post>() {
        override fun areItemsTheSame(oldItem: Post, newItem: Post): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Post, newItem: Post): Boolean {
            return oldItem.id == newItem.id
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        return PostViewHolder(ListItemPostBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val post = getItem(position)
        holder.itemView.setOnClickListener {
            onClickListener.onClick(post)
        }
        holder.bind(post)
    }

    class OnClickListener(val clickListener: (post:Post) -> Unit) {
        fun onClick(post:Post) = clickListener(post)
    }
}