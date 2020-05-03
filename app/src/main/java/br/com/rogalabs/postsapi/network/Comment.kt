package br.com.rogalabs.postsapi.network

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Gets Mars real estate property information from the Mars API Retrofit service and updates the
 * [Comments] and [CommentApiStatus] [LiveData]. The Retrofit service returns a coroutine
 * Deferred, which we await to get the result of the transaction.
 */

@Parcelize
class Comment(
        val postId: Int,
        val id: Int,
        val name: String,
        val email: String,
        val body: String) : Parcelable