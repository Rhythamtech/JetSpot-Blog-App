package com.rhytham.jetspot.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Post(
    @SerialName(value = "id")
    var postId:String,

    @SerialName(value = "post_title")
    var postTitle: String,

    @SerialName(value = "post_content")
    var postContent: String,

    @SerialName(value = "category")
    var category:String,

    @SerialName(value = "author_name")
    var authorName:String,

    @SerialName(value = "author_id")
    var authorId:String,

    @SerialName(value = "date_published")
    var datePublished: String
)
