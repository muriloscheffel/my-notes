package com.scheffel.mynotes.ui

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.BottomAppBarDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.scheffel.mynotes.ui.theme.MyNotesTheme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavController,
    // onNavigationIconClick: () -> Unit
) {

    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
//    var checked by remember { mutableStateOf(false) }

    val context = LocalContext.current
    var expanded by remember { mutableStateOf(false) }

    MyNotesTheme(/*darkTheme = checked*/) {
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
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(values)
            ) {
                items(50) {
                    Text(
                        text = "Item $it",
                        modifier = Modifier.padding(16.dp)
                    )
                }
            }
        }
    }
}