package com.rhytham.jetspot.network

import android.annotation.SuppressLint
import com.rhytham.jetspot.model.BlogPost
import com.rhytham.jetspot.network.ApiClient.client
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.patch
import io.ktor.client.request.post
import io.ktor.client.request.setBody

class ApiRepository {

    @SuppressLint("SimpleDateFormat")
    suspend fun getAllBlogpost(): List<BlogPost> = client.get(BlogApiRoutes.all).body()
    suspend fun getBlogpost(postId:String): BlogPost = client.get(BlogApiRoutes.getBlog) {
        parameter("row_id",postId)
    }.body()

    suspend fun createBlogpost(blogPost: BlogPost):BlogPost = client.post(BlogApiRoutes.create){
        setBody("{" +
                "    \"post_title\": \""+blogPost.postTitle+"\"," +
                "    \"post_content\": \""+blogPost.postContent+"\"," +
                "    \"category\": \""+blogPost.category+"\"," +
                "    \"date_published\": \""+blogPost.datePublished+"\"," +
                "    \"author\": [" +
                "        \""+blogPost.authorId+"\"" +
                "    ]" +
                "}")
    }.body()

    suspend fun updateBlogpost(blogPost: BlogPost):BlogPost = client.patch(BlogApiRoutes.update){
        setBody("{" +
                "    \"post_title\": \""+blogPost.postTitle+"\"," +
                "    \"post_content\": \""+blogPost.postContent+"\"," +
                "    \"category\": \""+blogPost.category+"\"," +
                "    \"date_published\": \""+blogPost.datePublished+"\"," +
                "    \"author\": [" +
                "        \""+blogPost.authorId+"\"" +
                "    ]" +
                "}")
        parameter("row_id",blogPost.postId)
    }.body()

    suspend fun deleteBlogpost(postId:String) {
        client.get(BlogApiRoutes.delete) {
            parameter("row_id",postId)
        }
    }




}