package br.com.rogalabs.postsapi.views

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.manoelh.postsapiandroid.R.layout.activity_main
import br.com.rogalabs.postsapi.adapter.PostListAdapter
import br.com.rogalabs.postsapi.interfaces.OnPostListInteractionListener
import br.com.rogalabs.postsapi.model.PostModel
import br.com.rogalabs.postsapi.service.ServiceRequest
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var mOnPostListInteractionListener: OnPostListInteractionListener
    private var mPostList: List<PostModel> = arrayListOf()
    private val mServiceRequest = ServiceRequest()
    private lateinit var postAdapter: PostListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activity_main)
        overwriteOnPostInteractionListener()
        setupObservers()
        loadRecyclerView()
    }

    private fun setupObservers(){
        mServiceRequest.searchPostsFromAPI().observe(this, Observer { posts ->
            if (posts != null){
                mPostList = posts.toMutableList()
                postAdapter.setData(mPostList)
            }
        })
    }

//    private fun searchPostsFromAPI() {
//        val posts: MutableList<PostModel>? = mutableListOf()
//        mServiceRequest.searchPostsFromAPI(apiCallback(posts))
//    }
//
//    private fun apiCallback(posts: MutableList<PostModel>?): (MutableList<PostModel>?) -> Unit {
//        return {
//            if(it == null)
//                buildToast(getString(R.string.response_unsuccessful))
//
//            else{
//                mPostList = it
//                loadRecyclerView()
//                //switchProgressVisibility()
//            }
//        }
//    }

    private fun overwriteOnPostInteractionListener() {
        mOnPostListInteractionListener = object : OnPostListInteractionListener {
            override fun onListClick(postId: Int) {
                //
            }
        }
        postAdapter = PostListAdapter(mPostList, mOnPostListInteractionListener)
    }

    private fun loadRecyclerView() {
        recyclerView.adapter = postAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
    }

    private fun buildToast(message: String){
        Toast.makeText(applicationContext, message, Toast.LENGTH_LONG).show()
    }
}
