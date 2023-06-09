package com.rhytham.jetspot.item

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.navigation.NavController

@Composable
fun AlertMessage(navController: NavController,alertText: String,openDialog: MutableState<Boolean>) {
    AlertDialog(
        text = {
            Text( text = alertText)
        },
        onDismissRequest = {
            openDialog.value = false
        },
        dismissButton = {
            TextButton(onClick = {
                openDialog.value = false

            }) {
                Text(text = "Dismiss")
            }
        },
        confirmButton = {
            TextButton(onClick = {
                navController.popBackStack()
                openDialog.value = false
            }) {
                Text(text = "Confirm")
            }
        },
    )

}