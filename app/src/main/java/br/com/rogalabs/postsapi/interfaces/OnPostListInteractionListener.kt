package br.com.rogalabs.postsapi.interfaces

import br.com.rogalabs.postsapi.model.PostModel

interface OnPostListInteractionListener {
    fun onListClick(postModel: PostModel)
}