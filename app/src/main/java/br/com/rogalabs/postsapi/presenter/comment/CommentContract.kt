package br.com.rogalabs.postsapi.presenter.comment

import br.com.rogalabs.postsapi.model.Comment

interface CommentContract {

    interface Presenter {

        fun onDestroy()

        fun requestDataFromServer(id: Int)
    }

    interface CommentView {

        fun showProgress()

        fun hideProgress()

        fun setDataCommentToRecyclerView(commentList: List<Comment>)

        fun onResponseFailure(throwable: Throwable)
    }

    interface GetCommentIntractor {

        interface OnFinishedListener {
            fun onFinished(comment: List<Comment>)
            fun onFailure(t: Throwable)
        }

        fun getCommentArrayList(id: Int, onFinishedListener: OnFinishedListener)
    }
}
