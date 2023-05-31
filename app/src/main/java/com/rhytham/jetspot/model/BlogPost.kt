package com.rhytham.jetspot.model

import java.util.Date

data class BlogPost(
    var postId:String,
    var postTitle: String,
    var postContent: String,
    var category:String,
    var authorName:String,
    var authorId:String,
    var datePublished:Date
)
