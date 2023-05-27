package com.rhytham.jetspot.screens

import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.google.gson.Gson
import com.rhytham.jetspot.item.AuthorDetailBar
import com.rhytham.jetspot.model.BlogPost
import com.rhytham.jetspot.navigation.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BlogpostScreen( post:String, navHostController: NavHostController) {
    val gson = Gson()
    val blogPost:BlogPost =  gson.fromJson(post,BlogPost::class.java)

    BackHandler(enabled = true) {
        navHostController.popBackStack()
    }

    Scaffold(
        floatingActionButton = {
            ExtendedFloatingActionButton(
                text = { Text("Edit post") },
                icon = { Icon(Icons.Filled.Edit, contentDescription = "") },
                onClick = {
                          navHostController.navigate(Screen.EditPost.passBlogPost(post))
                },
                containerColor = MaterialTheme.colorScheme.onBackground ,
                contentColor = MaterialTheme.colorScheme.background
            )
        }
    ) {it
        Column(
            modifier = Modifier
                .padding(all = 8.dp)
                .verticalScroll(
                    state = rememberScrollState(),
                )
                .fillMaxSize(),
            verticalArrangement = Arrangement.Top,
        ) {

            // Heading or Title
            Text(
                modifier = Modifier.padding(end = 4.dp),
                text = blogPost.postTitle,
                style = MaterialTheme.typography.titleLarge
            )

            //Author and blog detail
            AuthorDetailBar(authorName = blogPost.author, publishedDate = blogPost.datePublished)

            //Blog Post
            Text(
                modifier = Modifier.padding(all = 4.dp),
                text = blogPost.postContent,

                style = MaterialTheme.typography.bodyLarge
            )
        }


    }

}