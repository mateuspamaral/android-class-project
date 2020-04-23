package br.com.rogalabs.postsapi.views

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDialogFragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.rogalabs.postsapi.adapter.CommentListAdapter
import br.com.rogalabs.postsapi.model.CommentModel
import br.com.rogalabs.postsapi.model.PostModel
import br.com.rogalabs.postsapi.service.CommentServiceRequest
import com.manoelh.postsapiandroid.R
import kotlinx.android.synthetic.main.activity_main.*


class CommentDialogFragment(postSelected: PostModel): AppCompatDialogFragment() {
    private var mCommentsList: MutableList<CommentModel> = arrayListOf()
    private val mCommentServiceRequest = CommentServiceRequest()
    private lateinit var mContext: Context
    private lateinit var mRecyclerView: RecyclerView
    private var postModel: PostModel = postSelected
    private lateinit var mCommentListAdapter: CommentListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.DialogStyle)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val viewGroup = inflater.inflate(R.layout.comment, container)
        mContext = inflater.context
        mCommentListAdapter = CommentListAdapter(mCommentsList)
        mRecyclerView = viewGroup.findViewById(R.id.recyclerViewComments)
        setupCommentObserver(postModel.id)
        loadCommentRecyclerView()
        dialog!!.setTitle("${mContext.getString(R.string.comments)} ${postModel.title}")
        return viewGroup
    }

    private fun setupCommentObserver(postId: Int){
        mCommentServiceRequest.searchCommentsFromAPI(postId)
            .observe(this, Observer { comments ->
                if (comments != null){
                    mCommentsList = comments.toMutableList()
                    mCommentListAdapter.setData(mCommentsList)
                }
            })
    }

    private fun loadCommentRecyclerView() {
        mCommentsList.clear()
        mRecyclerView.setHasFixedSize(true)
        mRecyclerView.adapter = mCommentListAdapter
        mRecyclerView.layoutManager = LinearLayoutManager(mContext)
    }

}