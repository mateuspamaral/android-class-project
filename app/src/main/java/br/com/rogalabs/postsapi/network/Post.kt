package br.com.rogalabs.postsapi.network

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Gets Mars real estate property information from the Mars API Retrofit service and updates the
 * [Posts] and [PostApiStatus] [LiveData]. The Retrofit service returns a coroutine
 * Deferred, which we await to get the result of the transaction.
 */

@Parcelize
data class Post(
    val userId: Int,
    val id: Int,
    val title: String,
    val body: String) : Parcelable