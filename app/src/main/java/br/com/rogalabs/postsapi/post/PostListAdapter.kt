package br.com.rogalabs.postsapi.post

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import br.com.rogalabs.postsapi.databinding.ListItemPostBinding
import br.com.rogalabs.postsapi.network.Post

/**
 * This class implements a [RecyclerView] [ListAdapter] which uses Data Binding to present [List]
 * data, including computing diffs between lists.
 * @param onClick a lambda that takes the item cliked and send to next fragment
 */
class PostListAdapter(private val onClickListener: OnClickListener) :
        ListAdapter<Post, PostListAdapter.PostViewHolder>(DiffCallback) {

    /**
     * The PostViewHolder constructor takes the binding variable from the associated
     * list_tem_post, which nicely gives it access to the full [Post] information.
     */
    class PostViewHolder(private var binding: ListItemPostBinding):
            RecyclerView.ViewHolder(binding.root) {
        fun bind(post: Post) {
            binding.postVariable = post
            binding.executePendingBindings()
        }
    }

    /**
     * Allows the RecyclerView to determine which items have changed when the [List] of [Post]
     * has been updated.
     */
    companion object DiffCallback : DiffUtil.ItemCallback<Post>() {
        override fun areItemsTheSame(oldItem: Post, newItem: Post): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Post, newItem: Post): Boolean {
            return oldItem.id == newItem.id
        }
    }

    /**
     * Create new [RecyclerView] item views (invoked by the layout manager)
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        return PostViewHolder(ListItemPostBinding.inflate(LayoutInflater.from(parent.context)))
    }

    /**
     * Replaces the contents of a view (invoked by the layout manager)
     */
    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val post = getItem(position)
        holder.itemView.setOnClickListener {
            onClickListener.onClick(post)
        }
        holder.bind(post)
    }

    /**
     * Custom listener that handles clicks on [RecyclerView] items.  Passes the [Post]
     * associated with the current item to the [onClick] function.
     * @param clickListener lambda that will be called with the current [Post]
     */
    class OnClickListener(val clickListener: (post:Post) -> Unit) {
        fun onClick(post:Post) = clickListener(post)
    }
}