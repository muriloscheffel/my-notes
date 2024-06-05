package com.scheffel.mynotes.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.BottomAppBarDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.scheffel.mynotes.ui.note.Note
import com.scheffel.mynotes.ui.note.NoteDatabase
import com.scheffel.mynotes.ui.note.NoteViewModel
import com.scheffel.mynotes.ui.note.NoteViewModelFactory

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavController,
    noteViewModel: NoteViewModel
) {

    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()

    val context = LocalContext.current
    var expanded by remember { mutableStateOf(false) }

    val notes by noteViewModel.allNotes.collectAsState(initial = emptyList())


    Scaffold(

        modifier = Modifier
            .fillMaxSize()
            .nestedScroll(scrollBehavior.nestedScrollConnection),

        topBar = {
            TopAppBar(

                title = {
                    Text("My notes")
                },
                navigationIcon = {
                    IconButton(onClick = { /*onNavigationIconClick()*/ }) {
                        Icon(
                            imageVector = Icons.Default.Menu,
                            contentDescription = ""
                        )
                    }
                },
                actions = {
//                        Switch(
//                            checked = checked,
//                            onCheckedChange = { checked = it },
//                            modifier = Modifier.padding(end = 5.dp)
//                        )
                    Spacer(Modifier.width(5.dp))
                    IconButton(onClick = { expanded = !expanded }) {
                        Icon(imageVector = Icons.Default.MoreVert,
                            contentDescription = "",
                            modifier = Modifier.size(40.dp)
                        )
                    }

                    DropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false },
                        modifier = Modifier.width(200.dp)
                    ) {
                        DropdownMenuItem(
                            text = { Text(
                                text = "Account")
                            },
                            onClick = { /*TODO*/ }
                        )
                        DropdownMenuItem(
                            text = { Text("Settings") },
                            onClick = { navController.navigate("configuration") }
                        )
                    }
                },
                scrollBehavior = scrollBehavior
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate("write") },
                containerColor = BottomAppBarDefaults.bottomAppBarFabColor,
                elevation = FloatingActionButtonDefaults.bottomAppBarFabElevation()
            ) {
                Icon(Icons.Filled.Add, "")
            }
        }
    ) { values ->
        NoteList(notes, Modifier.padding(values))
    }
}

@Composable
fun NoteList(
    notes: List<Note>,
    modifier: Modifier
) {
    LazyColumn(modifier = modifier) {
        items(notes) { note ->
            NoteItem(note)
        }
    }
}

@Composable
fun NoteItem(
    note: Note
) {
    Column(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .height(50.dp)
    ) {
        Text(
            text = note.content,
            fontSize = 22.sp
        )
        Divider()
    }
}




//private lateinit var database: NoteDatabase
//
//@Preview
//@Composable
//private fun HomeScreenPreview() {
//
//    val navController = rememberNavController()
//
//
//    database = NoteDatabase.getDatabase(this)
//
//    val noteViewModel: NoteViewModel = viewModel(
//        factory = NoteViewModelFactory(database.noteDao())
//    )
//
//
//
//    HomeScreen(
//        navController = navController,
//        noteViewModel = noteViewModel
//    )
//}