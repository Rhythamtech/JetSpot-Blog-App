package com.rhytham.jetspot.item

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp

@Composable
fun EditorPostTopAppBar(appBarTitle:String,buttonText:String,onClick:()->Unit){

    Row( modifier = Modifier
        .padding(horizontal = 14.dp, vertical = 4.dp)
        .fillMaxWidth(),
    verticalAlignment = Alignment.CenterVertically,
    horizontalArrangement = Arrangement.SpaceBetween) {

        Text(
            text=appBarTitle,
            maxLines = 1,
            style = MaterialTheme.typography.titleLarge,
            overflow = TextOverflow.Ellipsis
        )

        //Publish Button
        Button(modifier = Modifier
            .width(120.dp)
            .padding(vertical = 4.dp),
            colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.onBackground),
            onClick = {
            }) {
            onClick()
            Text(
                textAlign = TextAlign.Center,
                text = buttonText
            )
        }
    }


}