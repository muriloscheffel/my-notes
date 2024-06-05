package com.scheffel.mynotes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
            // Variavel que altera o boolean do dark mode
            val checked by remember { mutableStateOf(mutableStateOf(true)) }

            // Controlador da navegação
            val navController = rememberNavController()

            // Configurando o tema dark
            MyNotesTheme(darkTheme = checked.value) {

                NavHost(navController = navController, startDestination = "home") {
                    // Tela principal
                    composable("home") {
                        HomeScreen(
                            navController = navController,
                        )
                    }
                    // Tela para escrever notas
                    composable("write") {
                        WriteScreen(
                            navController = navController,
                        )
                    }
                    // Tela de configuração
                    composable("configuration") {
                        ConfigurationScreen(
                            navController = navController,
                            checked = checked
                        )
                    }
                }

            }
        }
    }
}