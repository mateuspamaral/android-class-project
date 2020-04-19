package br.com.rogalabs.postsapi.view.fragments.post


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

import br.com.rogalabs.postsapi.R
import br.com.rogalabs.postsapi.model.Post
import br.com.rogalabs.postsapi.presenter.post.PostContract
import br.com.rogalabs.postsapi.presenter.post.PostIntractorImpl
import br.com.rogalabs.postsapi.view.adapters.PostAdapter
import kotlinx.android.synthetic.main.fragment_post.*

import br.com.rogalabs.postsapi.presenter.post.PostPresenterImpl
import android.support.v7.widget.DividerItemDecoration
import br.com.rogalabs.postsapi.utils.AndroidUtil
import br.com.rogalabs.postsapi.view.fragments.comment.CommentFragment


class PostFragment : Fragment(), PostContract.PostView {

    private var posts = ArrayList<Post>()
    private var adapter = PostAdapter(posts, this::onPostItemClick)

    var presenter: PostContract.Presenter? = null


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_post, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView()

        presenter = PostPresenterImpl(this, PostIntractorImpl())

        if(AndroidUtil.isOnline(context)){
            getPost()
        }else{
            AndroidUtil.alert(context)
        }
    }

    override fun setDataToRecyclerView(posts: List<Post>) {
        adapter.updateList(posts)
    }

    fun getPost(){
        presenter?.let {
            it.requestDataFromServer()
        }
    }

    private fun onPostItemClick(post: Post) {

        if(AndroidUtil.isOnline(context)){
            CommentFragment
                    .newInstance(post)
                    .open(requireFragmentManager())
        }else{
            AndroidUtil.alert(context)
        }
    }

    private fun initRecyclerView() {

        val layoutManager = LinearLayoutManager(requireContext())
        rvPost.layoutManager = layoutManager
        rvPost.addItemDecoration(DividerItemDecoration(rvPost.context, DividerItemDecoration.VERTICAL))

        rvPost.adapter = adapter
    }

    override fun onResponseFailure(throwable: Throwable) {
        Toast.makeText(requireContext(),
                "Connection Error" + throwable.message,
                Toast.LENGTH_LONG).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter?.onDestroy()
    }

    override fun showProgress() { progressBar.visibility = View.VISIBLE }

    override fun hideProgress() { progressBar.visibility = View.GONE }

}
