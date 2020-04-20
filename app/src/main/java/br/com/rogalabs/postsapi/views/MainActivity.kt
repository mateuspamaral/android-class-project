package br.com.rogalabs.postsapi.views

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.rogalabs.postsapi.adapter.CommentListAdapter
import com.manoelh.postsapiandroid.R.layout.activity_main
import br.com.rogalabs.postsapi.adapter.PostListAdapter
import br.com.rogalabs.postsapi.interfaces.OnPostListInteractionListener
import br.com.rogalabs.postsapi.model.CommentModel
import br.com.rogalabs.postsapi.model.PostModel
import br.com.rogalabs.postsapi.service.CommentServiceRequest
import br.com.rogalabs.postsapi.service.PostServiceRequest
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.comment.*

class MainActivity : AppCompatActivity() {

    private lateinit var mOnPostListInteractionListener: OnPostListInteractionListener
    private var mPostList: List<PostModel> = arrayListOf()
    //private var mCommentsList: List<CommentModel> = arrayListOf()
    private val mPostServiceRequest = PostServiceRequest()
    //private val mCommentServiceRequest = CommentServiceRequest()
    private lateinit var mPostListAdapter: PostListAdapter
    //private lateinit var mCommentListAdapter: CommentListAdapter
    private lateinit var mCommentDialog: CommentDialogFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activity_main)
        overwriteOnPostInteractionListener()
        setupAdapters()
        setupPostObserver()
        loadPostRecyclerView()
    }

    private fun setupAdapters() {
        mPostListAdapter = PostListAdapter(mPostList, mOnPostListInteractionListener)
        //mCommentListAdapter = CommentListAdapter(mCommentsList)
    }

    private fun setupPostObserver(){
        mPostServiceRequest.searchPostsFromAPI().observe(this, Observer { posts ->
            if (posts != null){
                mPostList = posts.toMutableList()
                mPostListAdapter.setData(mPostList)
            }
        })
    }

    private fun overwriteOnPostInteractionListener() {
        mOnPostListInteractionListener = object : OnPostListInteractionListener {
            override fun onListClick(postModel: PostModel) {
                openDialog(postModel)
                //loadCommentRecyclerView()
                //setupCommentObserver(postId)
            }
        }
    }

//    private fun setupCommentObserver(postId: Int){
//        mCommentServiceRequest.searchCommentsFromAPI(postId)
//            .observe(this, Observer { comments ->
//                if (comments != null){
//                    mCommentsList = comments.toMutableList()
//                    mCommentListAdapter.setData(mCommentsList)
//                }
//            })
//    }

    private fun openDialog(postModel: PostModel){
        CommentDialogFragment(postModel).show(supportFragmentManager, "dialog")
    }

    private fun loadPostRecyclerView() {
        recyclerViewPosts.adapter = mPostListAdapter
        recyclerViewPosts.layoutManager = LinearLayoutManager(this)
        recyclerViewPosts.setHasFixedSize(true)
    }

//    private fun loadCommentRecyclerView() {
//        recyclerViewComments.adapter = mCommentListAdapter
//        recyclerViewComments.layoutManager = LinearLayoutManager(this)
//        recyclerViewComments.setHasFixedSize(true)
//    }

    private fun buildToast(message: String){
        Toast.makeText(applicationContext, message, Toast.LENGTH_LONG).show()
    }
}
