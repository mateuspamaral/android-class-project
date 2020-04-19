package br.com.rogalabs.postsapi.presenter.comment


import br.com.rogalabs.postsapi.model.Comment

class CommentPresenterImpl(private var commentView: CommentContract.CommentView?, private val intractor: CommentContract.GetCommentIntractor) : CommentContract.Presenter, CommentContract.GetCommentIntractor.OnFinishedListener {


    override fun onDestroy() {
        this.commentView = null
    }

    override fun requestDataFromServer(id: Int) {
        commentView?.showProgress()
        intractor.getCommentArrayList(id, this)
    }

    override fun onFinished(comment: List<Comment>) {
        if (this.commentView != null) {
            commentView?.setDataCommentToRecyclerView(comment)
            commentView?.hideProgress()
        }
    }

    override fun onFailure(t: Throwable) {
        if (this.commentView != null) {
            commentView?.onResponseFailure(t)
            commentView?.hideProgress()
        }
    }
}