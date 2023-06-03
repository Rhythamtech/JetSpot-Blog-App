package com.rhytham.jetspot.item

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.rhytham.jetspot.R
import java.text.SimpleDateFormat
import java.util.Date

@SuppressLint("SimpleDateFormat")
@Composable
fun AuthorDetailBar(authorName:String, publishedDate: String){

    val defaultFormat = SimpleDateFormat("yyyy-MM-dd")
    val mainFormat = SimpleDateFormat("MMM dd, yyyy")

    Row(modifier = Modifier.fillMaxWidth()
        .padding(horizontal = 15.dp)
        .padding(top = 10.dp),
    verticalAlignment = Alignment.CenterVertically) {
        Image(modifier = Modifier.size(40.dp)
            .clip(CircleShape),
            painter = painterResource(id = R.drawable.profile_pic),
            contentDescription = null)
        Column(modifier = Modifier.padding(8.dp)) {
            Text(text = authorName,
            fontStyle = FontStyle.Italic,
            fontWeight = FontWeight.Bold)

            Text(text = "Admin",
                fontStyle = FontStyle.Italic)
        }

        Text(modifier = Modifier.fillMaxWidth(),
            text = mainFormat.format(defaultFormat.parse(publishedDate) as Date),
        textAlign = TextAlign.End)

    }
}