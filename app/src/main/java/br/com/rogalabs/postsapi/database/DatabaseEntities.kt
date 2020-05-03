package br.com.rogalabs.postsapi.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import br.com.rogalabs.postsapi.network.Post

@Entity
data class DatabasePost constructor(
        @PrimaryKey
        val userId: Int,
        val id: Int,
        val title: String,
        val body: String)

fun List<DatabasePost>.asDomainModel(): List<Post> {
    return map {
        Post(
                userId = it.userId,
                id = it.id,
                title = it.title,
                body = it.body)
    }
}
