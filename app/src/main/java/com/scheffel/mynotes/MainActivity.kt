package com.scheffel.mynotes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.scheffel.mynotes.ui.ConfigurationScreen
import com.scheffel.mynotes.ui.HomeScreen
import com.scheffel.mynotes.ui.WriteScreen
import com.scheffel.mynotes.ui.note.NoteDatabase
import com.scheffel.mynotes.ui.note.NoteViewModel
import com.scheffel.mynotes.ui.note.NoteViewModelFactory
import com.scheffel.mynotes.ui.theme.MyNotesTheme
class MainActivity : ComponentActivity() {
    // Variavel que armazena o banco de dados
    private lateinit var database: NoteDatabase


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Instanciando o banco de dados
        database = NoteDatabase.getDatabase(this)

        enableEdgeToEdge()

        setContent {
            // Variavel que altera o boolean do dark mode
            val checked by remember { mutableStateOf(mutableStateOf(true)) }

            // Controlador da navegação
            val navController = rememberNavController()

            // Instanciando o ViewModel
            val noteViewModel: NoteViewModel = viewModel(
                factory = NoteViewModelFactory(database.noteDao())
            )

            // Configurando o tema dark
            MyNotesTheme(darkTheme = checked.value) {

                NavHost(navController = navController, startDestination = "home") {
                    // Tela principal
                    composable("home") {
                        HomeScreen(
                            navController = navController,
                            noteViewModel
                        )
                    }
                    // Tela para escrever notas
                    composable("write") {
                        WriteScreen(
                            navController = navController,
                            database = database
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


