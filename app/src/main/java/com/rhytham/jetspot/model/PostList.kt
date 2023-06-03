package com.rhytham.jetspot.model

import kotlinx.serialization.Serializable

@Serializable
data class PostList(
    var count:Int,
    var next: String?,
    var previous:String?,
    var results:List<Post>
)
