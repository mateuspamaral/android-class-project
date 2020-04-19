package br.com.rogalabs.postsapi.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Comment(
        @SerializedName("name")
        val name: String? = null,

        @SerializedName("email")
        val email: String? = null,

        @SerializedName("body")
        val body: String? = null,

        @SerializedName("id")
        val id: Int = 0,

        @SerializedName("postId")
        val postId: Int = 0
) : Parcelable
