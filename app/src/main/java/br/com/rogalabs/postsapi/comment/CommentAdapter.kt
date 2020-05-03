package br.com.rogalabs.postsapi.comment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import br.com.rogalabs.postsapi.databinding.ListItemCommentBinding
import br.com.rogalabs.postsapi.network.Comment

/**
 * This class implements a [RecyclerView] [ListAdapter] which uses Data Binding to present [List]
 * data, including computing diffs between lists.
 */
class CommentAdapter() :
        ListAdapter<Comment, CommentAdapter.CommentViewHolder>(DiffCallback) {

    /**
     * The CommentViewHolder constructor takes the binding variable from the associated
     * list_item_comment, which nicely gives it access to the full [Comment] information.
     */
    class CommentViewHolder(private var binding: ListItemCommentBinding):
            RecyclerView.ViewHolder(binding.root) {
        fun bind(comment: Comment) {
            binding.commentVariable = comment
            // This is important, because it forces the data binding to execute immediately,
            // which allows the RecyclerView to make the correct view size measurements
            binding.executePendingBindings()
        }
    }

    /**
     * Allows the RecyclerView to determine which items have changed when the [List] of [Comment]
     * has been updated.
     */
    companion object DiffCallback : DiffUtil.ItemCallback<Comment>() {
        override fun areItemsTheSame(oldItem: Comment, newItem: Comment): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Comment, newItem: Comment): Boolean {
            return oldItem.id == newItem.id
        }
    }

    /**
     * Create new [RecyclerView] item views (invoked by the layout manager)
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder {
        return CommentViewHolder(ListItemCommentBinding.inflate(LayoutInflater.from(parent.context)))
    }

    /**
     * Replaces the contents of a view (invoked by the layout manager)
     */
    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
        val post = getItem(position)
        holder.bind(post)
    }
}