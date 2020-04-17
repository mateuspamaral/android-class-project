package br.com.rogalabs.postsapi.presenter.post

import br.com.rogalabs.postsapi.model.Post

class PostPresenterImpl( var postView: PostContract.PostView?,  var intractor: PostContract.GetPostIntractor ) : PostContract.Presenter, PostContract.GetPostIntractor.OnFinishedListener {

    override fun onDestroy() {
        this.postView = null
    }

    override fun requestDataFromServer() {
        postView?.showProgress()
        intractor.getPostArrayList(this)
    }

    override fun onFinished(posts: List<Post>) {
        if (this.postView != null) {
            postView?.setDataToRecyclerView(posts)
            postView?.hideProgress()
        }
    }

    override fun onFailure(t: Throwable) {
        if (this.postView != null) {
            postView?.onResponseFailure(t)
            postView?.hideProgress()
        }
    }
}