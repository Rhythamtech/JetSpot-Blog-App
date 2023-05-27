package com.rhytham.jetspot.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.navigation.NavHostController
import com.google.gson.Gson
import com.rhytham.jetspot.item.PostItem
import com.rhytham.jetspot.model.ApiToken
import com.rhytham.jetspot.model.BlogPost
import com.rhytham.jetspot.navigation.Screen
import com.rhytham.jetspot.network.ApiRepository

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen( navHostController: NavHostController){
    Scaffold(
        modifier = Modifier.fillMaxSize(),
    topBar = {
        CenterAlignedTopAppBar(
            title = {
            Text(
                "JetSpot Blog App",
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        })
    }){
        val apiRepo = ApiRepository()
        val gson = Gson()

        val blogPostList = produceState<List<BlogPost>>(
            initialValue = emptyList(),
            producer = {
                value = apiRepo.getAllBlogpost(tableId = ApiToken.post_table_id)
            })

        LazyColumn(contentPadding = it) {
            items(blogPostList.value) {post->
                PostItem(blogPost = post,
                onClick = {
                    navHostController.navigate(
                        Screen.Blog.passBlogPost(gson.toJson(post)
                        ))
                })
            }
        }
    }
}