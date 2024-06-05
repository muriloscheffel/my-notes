package com.scheffel.mynotes

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.scheffel.mynotes.ui.ConfigurationScreen
import com.scheffel.mynotes.ui.HomeScreen
import com.scheffel.mynotes.ui.WriteScreen

object NotesDestinations {
    const val HOME_ROUTE = "home"
    const val WRITE_ROUTE = "write"
    const val CONFIGURATION_ROUTE = "configuration"
}

@Composable
fun NotesNavGraph(navController: NavController) {

}