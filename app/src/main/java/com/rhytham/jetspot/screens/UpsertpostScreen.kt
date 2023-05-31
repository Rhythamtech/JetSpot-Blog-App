package com.rhytham.jetspot.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.google.gson.Gson
import com.rhytham.jetspot.item.EditorPostTopAppBar
import com.rhytham.jetspot.model.BlogPost

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UpsertpostScreen(post: String, navHostController: NavHostController) {
    val gson = Gson()
    val blogPost: BlogPost = gson.fromJson(post, BlogPost::class.java)
    var wordCount by remember {
        mutableStateOf(0)
    }

    var title by remember { mutableStateOf(blogPost.postTitle) }
    var postBody by remember { mutableStateOf(blogPost.postContent) }
    var category by remember { mutableStateOf(blogPost.category) }


    wordCount = postBody.split("\\s+".toRegex()).size


    BackHandler(enabled = true) {
        navHostController.popBackStack()
    }
    Column(
        modifier = Modifier
            .padding(all = 14.dp)
            .verticalScroll(
                state = rememberScrollState(),
            )
            .fillMaxSize()
    ) {

        EditorPostTopAppBar( appBarTitle = "Update Post",
            buttonText = "Update",
            onClick = {
            // On Publish Button CLick
        })

        // Heading or Title
        OutlinedTextField(
            modifier = Modifier
                .padding(vertical = 4.dp)
                .fillMaxWidth(),
            value = title,
            label = {
                Text(
                    text = "Title",
                    style = MaterialTheme.typography.labelMedium,
                    modifier = Modifier.padding(horizontal = 4.dp)
                )
            },
            onValueChange = {
                title = it
            },
            textStyle = MaterialTheme.typography.titleLarge
        )

        //Blog Post
        OutlinedTextField(
            modifier = Modifier
                .fillMaxSize(),
            value = postBody,
            label = {
                Text(
                    text = "Content Body",
                    style = MaterialTheme.typography.labelMedium,
                    modifier = Modifier.padding(horizontal = 4.dp)
                )
            },

            supportingText = {
                Text(
                    text = "Word count: $wordCount",
                    style = MaterialTheme.typography.labelMedium,
                    modifier = Modifier.padding(horizontal = 4.dp)
                )
            },
            maxLines = 8,
            onValueChange = {
                postBody = it
            },
            textStyle = MaterialTheme.typography.bodyLarge
        )

        //Category
        OutlinedTextField(
            modifier = Modifier
                .padding(vertical = 4.dp)
                .fillMaxWidth(),
            value = category,
            label = {
                Text(
                    text = "Category",
                    style = MaterialTheme.typography.labelMedium,
                    modifier = Modifier.padding(horizontal = 4.dp)
                )
            },
            onValueChange = {
                category = it
            },
            textStyle = MaterialTheme.typography.bodyLarge
        )

        SuggestionChip(onClick = { /*TODO*/ },
            label = {
                Text(text = blogPost.author)
            })


    }
}

@Preview(showBackground = true)
@Composable
fun PreviewUpsertpostScreen() {

    var str =
        "{\"author\":\"Jane Appleseed\",\"category\":\"Open Source\",\"datePublished\":\"Dec 29, 2019 12:00:00 AM\",\"postContent\":\"Lorem ipsum dolor sit amet consectetur adipiscing elit pretium, cubilia nunc suspendisse nam velit praesent tristique magnis at, imperdiet eleifend diam sociis porttitor facilisis erat. Nullam ut nam cursus est vitae posuere, justo nulla pellentesque neque molestie etiam vivamus, lacinia sed imperdiet facilisi quam. Tempor gravida magnis fringilla tellus class pharetra ligula dis, aptent taciti dictumst leo inceptos dictum aliquet rhoncus, netus ad nibh sit massa imperdiet scelerisque. Ultrices integer suspendisse pretium mauris dapibus eu ligula tellus, cubilia bibendum volutpat nulla mi pulvinar tempor habitant, ut ullamcorper convallis mollis odio elementum rutrum. Urna ante vestibulum habitant primis diam maecenas interdum, senectus proin ipsum suspendisse nam scelerisque odio morbi, ut etiam velit ad dolor sem.\",\"postId\":\"5yvzfosAzcb7S3WFTnuvdA\",\"postTitle\":\"10 Open Source Alternatives to Popular Apps\"}\n"

    UpsertpostScreen(post = str, navHostController = rememberNavController())
}
