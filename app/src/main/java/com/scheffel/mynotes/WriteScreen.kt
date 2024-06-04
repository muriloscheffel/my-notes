package com.scheffel.mynotes

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.scheffel.mynotes.ui.theme.MyNotesTheme
import kotlin.math.sin

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WriteScreen(
    navController: NavController,
    modifier: Modifier = Modifier,

) {

    var txtTitle by remember { mutableStateOf("") }
    var txtNote by remember { mutableStateOf("") }


    Scaffold(
        topBar = {
            TopAppBar(
                title = { /*TODO*/ },
                navigationIcon = {
                    IconButton(onClick = { navController.navigate("home") }) {
                        Icon(Icons.Default.ArrowBack, null)
                    }
                },
                modifier = Modifier.padding(bottom = 8.dp)
            )
        }
    ) { values ->
        Column(
            modifier = Modifier
                .padding(values)
                .fillMaxSize()
        ) {
            TransparentTextFields(
                modifier = Modifier.fillMaxWidth(),
                value = txtTitle,
                onValueChange = { txtTitle = it },
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    autoCorrect = false,
                    imeAction = ImeAction.Next
                ),
                //trailerIcon = { },
                placeholder = "Title"
            )
            TransparentTextFields(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(750.dp),
                value = txtNote,
                onValueChange = { txtNote = it },
                singleLine = false,
                keyboardOptions = KeyboardOptions(
                    autoCorrect = false,
                    imeAction = ImeAction.None
                ),
                //trailerIcon = { },
                placeholder = "Note"
            )
        }
    }
}

@Composable
fun TransparentTextFields(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    singleLine: Boolean,
    keyboardOptions: KeyboardOptions,
    //trailerIcon: () -> Unit,
    placeholder: String
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        placeholder = { Text(
            text = placeholder,
            style = TextStyle(
                color = Color.Gray
            ),
            fontSize = 20.sp
        ) },
        singleLine = singleLine,
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Transparent),
        keyboardOptions = keyboardOptions,
//        keyboardOptions = KeyboardOptions(
//            autoCorrect = false,
//            imeAction = ImeAction.Next
//        ),
        colors = TextFieldDefaults.colors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
            errorIndicatorColor = Color.Transparent,
            cursorColor = Color.White,
            unfocusedContainerColor = Color.Transparent,
            focusedContainerColor = Color.Transparent
        ),
        shape = RoundedCornerShape(6.dp),
//        trailerIcon = trailerIcon
        trailingIcon = {
            if(value.isNotEmpty()) {
                IconButton(onClick = { onValueChange("") }) {
                    Icon(Icons.Default.Close, null)
                }
            }
        },
    )
}