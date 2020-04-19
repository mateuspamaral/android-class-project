package br.com.rogalabs.postsapi.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Post (

    @SerializedName("title")
    val title: String? = null,

    @SerializedName("body")
    val body: String? = null,

    @SerializedName("id")
    val id: Int = 0,

    @SerializedName("userId")
    val userId: Int = 0
):Parcelable