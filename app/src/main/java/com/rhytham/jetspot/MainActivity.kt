package com.rhytham.jetspot

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.rhytham.jetspot.item.AlertMessage
import com.rhytham.jetspot.navigation.SetupNavGraph
import com.rhytham.jetspot.ui.theme.JetSpotBlogTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetSpotBlogTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    val openDialog = remember {
                        mutableStateOf(true)
                    }

                    if(InternetIsConnected()){
                        SetupNavGraph(navController = navController)
                    }
                    else{
                       AlertMessage(navController = navController, alertText = "Check your Internet Connection. We are unable to connect to our server", openDialog = openDialog  )
                    }


                }
            }
        }
    }
}

fun InternetIsConnected(): Boolean {
    return try {
        val command = "ping -c 1 google.com"
        Runtime.getRuntime().exec(command).waitFor() == 0
    } catch (e: Exception) {
        false
    }
}
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    JetSpotBlogTheme {
        //HomeScreen()
    }
}