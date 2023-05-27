package com.rhytham.jetspot.navigation

sealed class Screen( val route: String) {
    object  Home : Screen("home_screen")
    object  Blog : Screen("blog_screen/{blog_post}"){
        fun passBlogPost(post:String):String{
            return "blog_screen/$post"
        }
    }
    object EditPost : Screen("upsert_screen/{blog_post}"){
        fun passBlogPost(post:String):String{
            return "upsert_screen/$post"
        }
    }
}