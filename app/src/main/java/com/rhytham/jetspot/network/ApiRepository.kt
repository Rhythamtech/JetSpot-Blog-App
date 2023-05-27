package com.rhytham.jetspot.network

import android.annotation.SuppressLint
import android.util.Log
import com.rhytham.jetspot.model.BlogPost
import com.rhytham.jetspot.network.ApiClient.client
import com.rhytham.jetspot.network.ApiRoutes.tableRowsUrl
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.statement.bodyAsText
import org.json.JSONObject
import java.text.SimpleDateFormat
import java.util.Date

class ApiRepository {

    @SuppressLint("SimpleDateFormat")
    suspend fun getAllBlogpost(tableId: String): List<BlogPost> {

        val allPost = mutableListOf<BlogPost>()

        val httpResult = client.get("$tableRowsUrl$tableId/") {
            parameter("user_field_names", "true")
        }.bodyAsText()

        val postList = JSONObject(httpResult).getJSONArray("results")

        for (index in 0 until postList.length()) {
            val obj = postList.getJSONObject(index)
            allPost.add( convertToBlogPostObj(obj))
        }

        Log.d("postList", allPost.toString())
        return allPost
    }

    private fun convertToBlogPostObj(jsonObj:JSONObject): BlogPost {
        val sdf = SimpleDateFormat("YYYY-MM-dd")

        return BlogPost(
            postId = jsonObj.getString("id"),
            postTitle = jsonObj.getString("post_title"),
            postContent = jsonObj.getString("post_content"),
            category = jsonObj.getString("category"),
            author = jsonObj.getJSONArray("author").getJSONObject(0).getString("value"),
            datePublished = sdf.parse(jsonObj.getString("date_published")) as Date
        )

    }
}