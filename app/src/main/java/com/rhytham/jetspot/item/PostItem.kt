package com.rhytham.jetspot.item

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rhytham.jetspot.model.Post
import java.util.Date

@SuppressLint("SimpleDateFormat")
@Composable
fun PostItem(blogPost: Post, onClick : ()->Unit) {


    Card(
        modifier = Modifier
            .padding(all = 10.dp)
            .border(
                border = BorderStroke(width = 2.dp, MaterialTheme.colorScheme.onBackground),
                shape = RoundedCornerShape(9.dp)
            )
            .padding(all = 8.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background
        )
    ) {
        // Heading or Title
        Text(
            modifier = Modifier.padding(end = 4.dp),
            text = blogPost.postTitle,
            style = MaterialTheme.typography.titleMedium
        )

        Text(
            modifier = Modifier.padding(all = 4.dp),
            text = blogPost.postContent,
            style = MaterialTheme.typography.bodyLarge,
            maxLines = 4,
            overflow = TextOverflow.Ellipsis
        )

        //Author and blog detail
        AuthorDetailBar(authorName = blogPost.authorName, publishedDate = blogPost.datePublished)

        //Read More Button
        Button(modifier = Modifier
            .fillMaxWidth()
            .padding(all = 4.dp),
            colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.onBackground),
            onClick = {
                onClick()
            }) {
            Text(
                textAlign = TextAlign.Center,
                text = "Read More"
            )
        }

    }
}

@Preview(showBackground = true)
@Composable
fun PreviewPostItem() {

    PostItem(
        blogPost = Post(
            postId = "hdkdhh12j48dhr",
            postTitle = "The Benefits of Using a Blog Template",
            category = "Blogging",
            postContent = "Using a blog template can help you manage every aspect of your blog posts from one unified place. You can organize, track, and share your blog posts without missing a deadline. This can save you time and effort while ensuring that your blog remains consistent and professional.",
            authorName = "John Doe",
            authorId = "jdgjd7yd944",
            datePublished = Date().toString()
        ) ,
        onClick = {}
    )

}