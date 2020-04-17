package br.com.rogalabs.postsapi.view.fragments.comment

import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v4.app.FragmentManager
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import br.com.rogalabs.postsapi.R
import br.com.rogalabs.postsapi.model.Comment
import br.com.rogalabs.postsapi.model.Post
import br.com.rogalabs.postsapi.presenter.comment.CommentContract
import br.com.rogalabs.postsapi.presenter.comment.CommentIntractorImpl
import br.com.rogalabs.postsapi.presenter.comment.CommentPresenterImpl
import br.com.rogalabs.postsapi.view.adapters.CommentAdapter
import kotlinx.android.synthetic.main.fragment_comment.*
import kotlinx.android.synthetic.main.fragment_post.progressBar




class CommentFragment : DialogFragment(), CommentContract.CommentView {

    private var comments = ArrayList<Comment>()
    private var adapter = CommentAdapter(comments, this::onCommentItemClick)

    var presenter: CommentContract.Presenter? = null

    override fun onStart() {
        super.onStart()
        val dialog = dialog
        if (dialog != null) {
            val width = ViewGroup.LayoutParams.MATCH_PARENT
            val height = ViewGroup.LayoutParams.MATCH_PARENT
            dialog.window.setLayout(width, height)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_comment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val post = arguments?.getParcelable<Post>(EXTRA_POST)

        post?.let {
            initRecyclerView()
            presenter = CommentPresenterImpl(this, CommentIntractorImpl())

            tv_title.text = getString(R.string.posts_comment,it.title)

            getComment(it.id)
        }
    }

    fun open(fm: FragmentManager) {
        if (fm.findFragmentByTag(DIALOG_TAG) == null) {
            show(fm, DIALOG_TAG)
        }
    }

    override fun setDataCommentToRecyclerView(commentList: List<Comment>) {
        adapter.updateList(commentList)
    }

    private fun onCommentItemClick(comment: Comment) {

    }

    fun getComment(id: Int){
        presenter?.let {
            it.requestDataFromServer(id)
        }
    }

    private fun initRecyclerView() {

        val layoutManager = LinearLayoutManager(requireContext())
        rvCommentt.layoutManager = layoutManager

        rvCommentt.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))

        rvCommentt.adapter = adapter
    }

    override fun onResponseFailure(throwable: Throwable) {
        Toast.makeText(requireContext(),
                "Connection Error" + throwable.message,
                Toast.LENGTH_LONG).show()
    }

    override fun showProgress() { progressBar.visibility = View.VISIBLE }

    override fun hideProgress() { progressBar.visibility = View.GONE }

    companion object {
        private const val DIALOG_TAG = "editDialog"
        private const val EXTRA_POST = "post"

        fun newInstance(post: Post) = CommentFragment().apply {
            arguments = Bundle().apply {
                putParcelable(EXTRA_POST, post)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter?.onDestroy()
    }

}
