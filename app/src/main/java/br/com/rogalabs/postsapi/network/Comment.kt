package br.com.rogalabs.postsapi.network

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class Comment(
        val postId: Int,
        val id: Int,
        val name: String,
        val email: String,
        val body: String) : Parcelable