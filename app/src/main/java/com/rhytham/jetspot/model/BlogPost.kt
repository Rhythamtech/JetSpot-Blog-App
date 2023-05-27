package com.rhytham.jetspot.model

import java.util.Date

data class BlogPost(
    var postId:String,
    var postTitle: String,
    var category:String,
    var postContent: String,
    var author:String,
    var datePublished:Date
)
