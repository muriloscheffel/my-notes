package com.scheffel.mynotes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.scheffel.mynotes.ui.ConfigurationScreen
import com.scheffel.mynotes.ui.HomeScreen
import com.scheffel.mynotes.ui.WriteScreen
import com.scheffel.mynotes.ui.theme.MyNotesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            MyNotesTheme {

                var txtTitle by remember { mutableStateOf("") }

                val navController = rememberNavController()

                NavHost(navController = navController, startDestination = "home") {
                    composable("home") {
                        HomeScreen(
                            navController = navController,
                        )
                    }
                    composable("write") {
                        WriteScreen(
                            navController = navController,
                        )
                    }
                    composable("configuration") {
                        ConfigurationScreen(
                            navController = navController
                        )
                    }
                }

            }
        }
    }
}