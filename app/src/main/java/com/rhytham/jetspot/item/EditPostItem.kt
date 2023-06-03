package com.rhytham.jetspot.item

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.AssistChip
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rhytham.jetspot.model.Post
import java.text.SimpleDateFormat
import java.util.Date

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("SimpleDateFormat")
@Composable
fun EditPostItem(blogPost: Post, onEditClick: () -> Unit,onDeleteClick: () -> Unit) {
    val defaultFormat = SimpleDateFormat("yyyy-MM-dd")
    val mainFormat = SimpleDateFormat("MMM dd, yyyy")

    Column(modifier = Modifier
        .padding(all = 10.dp)
        .border(
            border = BorderStroke(width = 2.dp, MaterialTheme.colorScheme.onBackground),
            shape = RoundedCornerShape(9.dp)
        )
        .padding(all = 8.dp)
        .fillMaxWidth()) {
        // Heading or Title
        Text(
            modifier = Modifier
                .padding(end = 4.dp),
            text = blogPost.postTitle,
            overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.titleMedium
        )

        Text(
            modifier = Modifier
                .padding(end = 4.dp),
            text ="Published on: ${mainFormat.format(defaultFormat.parse(blogPost.datePublished) as Date)}",
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {

           AssistChip(
               modifier = Modifier.padding(all = 4.dp),
               label = {
                       Text(text = "Published")
               },
               onClick = { /*TODO*/ })
            FilledIconButton(
                onClick = onEditClick,
                shape = ShapeDefaults.Small,
                colors = IconButtonDefaults.filledIconButtonColors(
                    containerColor = MaterialTheme.colorScheme.secondaryContainer
                )
            ) {
                Icon(imageVector = Icons.Filled.Edit, contentDescription = "Edit Icon")
            }
            FilledIconButton(
                onClick = onDeleteClick,
                shape = ShapeDefaults.Small,
                colors = IconButtonDefaults.filledIconButtonColors(
                    containerColor = MaterialTheme.colorScheme.error
                )
            ) {
                Icon(imageVector = Icons.Filled.Delete, contentDescription = "Delete Icon")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewEditPostItem() {

    EditPostItem(
        blogPost = Post(
            postId = "hdkdhh12j48dhr",
            postTitle = "The Benefits of Using a Blog Template",
            category = "Blogging",
            postContent = "Using a blog template can help you manage every aspect of your blog posts from one unified place. You can organize, track, and share your blog posts without missing a deadline. This can save you time and effort while ensuring that your blog remains consistent and professional.",
            authorName = "John Doe",
            authorId = "jdgjd7yd944",
            datePublished = "2023-10-22"
        ),
        onEditClick = {},
        onDeleteClick = {}
    )

}