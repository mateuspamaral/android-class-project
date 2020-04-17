package br.com.rogalabs.postsapi.presenter.post

import br.com.rogalabs.postsapi.model.Post

interface PostContract {

    interface Presenter {

        fun onDestroy()

        fun requestDataFromServer()
    }

    interface PostView {

        fun showProgress()

        fun hideProgress()

        fun setDataToRecyclerView(posts: List<Post>)

        fun onResponseFailure(throwable: Throwable)
    }

    interface GetPostIntractor {

        interface OnFinishedListener {
            fun onFinished(posts: List<Post>)
            fun onFailure(t: Throwable)
        }

        fun getPostArrayList(onFinishedListener: OnFinishedListener)
    }
}
