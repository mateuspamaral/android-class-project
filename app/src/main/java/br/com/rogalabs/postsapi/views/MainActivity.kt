package br.com.rogalabs.postsapi.views

import android.os.Bundle
import android.view.WindowManager
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.rogalabs.postsapi.adapter.PostListAdapter
import br.com.rogalabs.postsapi.interfaces.OnPostListInteractionListener
import br.com.rogalabs.postsapi.model.PostModel
import br.com.rogalabs.postsapi.service.PostServiceRequest
import com.manoelh.postsapiandroid.R.layout.activity_main
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private lateinit var mOnPostListInteractionListener: OnPostListInteractionListener
    private var mPostList: List<PostModel> = arrayListOf()
    private val mPostServiceRequest = PostServiceRequest()
    private lateinit var mPostListAdapter: PostListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activity_main)
        switchProgressVisibility()
        overwriteOnPostInteractionListener()
        setupAdapters()
        setupPostObserver()
        loadPostRecyclerView()
    }

    private fun setupAdapters() {
        mPostListAdapter = PostListAdapter(mPostList, mOnPostListInteractionListener)
    }

    private fun setupPostObserver(){
        mPostServiceRequest.searchPostsFromAPI().observe(this, Observer { posts ->
            if (posts != null){
                mPostList = posts.toMutableList()
                mPostListAdapter.setData(mPostList)
                switchProgressVisibility()
            }
        })
    }

    private fun overwriteOnPostInteractionListener() {
        mOnPostListInteractionListener = object : OnPostListInteractionListener {
            override fun onListClick(postModel: PostModel) {
                openDialog(postModel)
            }
        }
    }

    private fun openDialog(postModel: PostModel){
       CommentDialogFragment(postModel).showNow(supportFragmentManager, "dialog")
    }

    private fun loadPostRecyclerView() {
        recyclerViewPosts.adapter = mPostListAdapter
        recyclerViewPosts.layoutManager = LinearLayoutManager(this)
        recyclerViewPosts.setHasFixedSize(true)
    }

    private fun switchProgressVisibility() = if (progressBar.visibility == ProgressBar.VISIBLE) {
        progressBar.visibility = ProgressBar.INVISIBLE
        window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
    }
    else {
        progressBar.visibility = ProgressBar.VISIBLE
        window.setFlags(
            WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
            WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
    }
}
