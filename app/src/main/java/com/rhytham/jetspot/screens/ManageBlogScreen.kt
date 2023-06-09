package com.rhytham.jetspot.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.gson.Gson
import com.rhytham.jetspot.item.EditPostItem
import com.rhytham.jetspot.model.Post
import com.rhytham.jetspot.navigation.Screen
import com.rhytham.jetspot.network.ApiRepository
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ManageBlogScreen(navController: NavController) {
    BackHandler(enabled = true) {
        navController.popBackStack()
    }


    Scaffold(
        modifier = Modifier.padding(horizontal = 8.dp),
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Manage Blog Post")
                }
            )
        },
        floatingActionButton = {
            ExtendedFloatingActionButton(
                text = { Text(text = "Create") },
                icon = { Icon(imageVector = Icons.Default.Create, contentDescription = "Create Icon") },
                onClick = { navController.navigate(Screen.EditPost.route) },
                containerColor = MaterialTheme.colorScheme.onBackground ,
                contentColor = MaterialTheme.colorScheme.background)
        }
    ) { contentPadding ->
        val apiRepo = ApiRepository()
        val gson = Gson()
        val scope = rememberCoroutineScope()

        val blogList = produceState<List<Post>>(
            initialValue = emptyList(), producer = {
                value = apiRepo.getAllBlogpost().results
            })

        LazyColumn(contentPadding = contentPadding) {
            items(blogList.value) { post ->
                EditPostItem(
                    blogPost = post,
                    onEditClick = {
                        navController.navigate(
                            Screen.EditPost.passBlogPost(
                                post = gson.toJson(post)
                            )
                        )
                    },
                    onDeleteClick = {
                        scope.launch {
                            apiRepo.deleteBlogpost(postId = post.postId)
                        }
                    }
                )
            }
        }
    }
}