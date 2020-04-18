package br.com.rogalabs.postsapi.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.manoelh.postsapiandroid.R
import br.com.rogalabs.postsapi.interfaces.OnPostListInteractionListener
import br.com.rogalabs.postsapi.model.PostModel
import br.com.rogalabs.postsapi.viewHolder.PostViewHolder

class PostListAdapter(private var postList: List<PostModel>,
                      private val onPostListInteractionListener: OnPostListInteractionListener
):
                      RecyclerView.Adapter<PostViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val inflate = LayoutInflater.from(parent.context)
        val view = inflate.inflate(R.layout.posts , parent, false)
        return PostViewHolder(view, parent.context, onPostListInteractionListener)
    }

    override fun getItemCount(): Int {
        return postList.count()
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.bindTask(postList[position])
    }

    fun setData(list: List<PostModel>){
        this.postList = list
        this.notifyDataSetChanged()
    }
}