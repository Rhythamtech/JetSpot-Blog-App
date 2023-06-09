package com.rhytham.jetspot.network

import android.annotation.SuppressLint
import com.rhytham.jetspot.model.Post
import com.rhytham.jetspot.model.PostList
import com.rhytham.jetspot.network.ApiClient.client
import io.ktor.client.call.body
import io.ktor.client.request.accept
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.patch
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType

class ApiRepository {
    @SuppressLint("SimpleDateFormat")
    suspend fun getAllBlogpost(): PostList = client.get(BlogApiRoutes.all).body()


    suspend fun getBlogpost(postId: String): Post = client.get(BlogApiRoutes.getBlog) {
        parameter("row_id", postId)
    }.body()

    suspend fun createBlogpost(blogPost: Post): Post = client.post(BlogApiRoutes.create) {
        accept(ContentType.Application.Json)
        setBody(
            "{" +
                    "    \"post_title\": \"" + blogPost.postTitle + "\"," +
                    "    \"post_content\": \"" + blogPost.postContent + "\"," +
                    "    \"category\": \"" + blogPost.category + "\"," +
                    "    \"date_published\": \"" + blogPost.datePublished + "\"," +
                    "    \"author\": [" +
                    "        \"" + blogPost.authorId + "\"" +
                    "    ]" +
                    "}"
        )
    }.body()

    suspend fun updateBlogpost(blogPost: Post): Post = client.post(BlogApiRoutes.update+"?row_id=${blogPost.postId}") {
      //  contentType(ContentType.Application.Json.withParameter("charset", "utf-8"))
        setBody(
                "{" +
                        "    \"post_title\": \"" + blogPost.postTitle + "\"," +
                        "    \"post_content\": \"" + blogPost.postContent + "\"," +
                        "    \"category\": \"" + blogPost.category + "\"," +
                        "    \"date_published\": \"" + blogPost.datePublished + "\"," +
                        "    \"author\": [" +
                        "        \"" + blogPost.authorId + "\"" +
                        "    ]" +
                        "}"
            )
        }.body()


    suspend fun deleteBlogpost(postId: String) {
        client.get(BlogApiRoutes.delete) {
            parameter("row_id", postId)
        }
    }


}
