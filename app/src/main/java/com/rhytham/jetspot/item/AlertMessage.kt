package com.rhytham.jetspot.item

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Composable
fun AlertMessage(
    navController: NavController,
    alertText: String,
    openDialog: MutableState<Boolean>,
) {
    AlertDialog(
        text = {
            Text(text = alertText)
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

//@Composable
//fun AlertLoader(
//    navController: NavController,
//    alertText: String,
//    openDialog: MutableState<Boolean>,
//) {
//    Dialog(onDismissRequest = {
//        openDialog.value = false
//    }
//    ) {
//        Box(
//            contentAlignment = Center,
//            modifier = Modifier
//                .size(100.dp)
//                .background(White, shape = RoundedCornerShape(8.dp))
//        ) {
//            CircularProgressIndicator()
//        }
//    }
//}
