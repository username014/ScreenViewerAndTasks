@file:Suppress("UNCHECKED_CAST")

package com.example.applicationtype1

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.ui.platform.LocalContext
import androidx.compose.foundation.layout.Column
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Icon
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.TextButton
import androidx.compose.material3.IconButton
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.MaterialTheme as MaterialThemeB

private val DarkGreenColorScheme = darkColorScheme(
    primary = Color(0xFF4CAF50),
    onPrimary = Color.White,
    secondary = Color(0xFF81C784),
    onSecondary = Color.Black,
    background = Color(0xFF121212),
    onBackground = Color.White,
    surface = Color(0xFF1E1E1E),
    onSurface = Color.White,
    error = Color(0xFFCF6679),
    onError = Color.Black
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            MaterialThemeB(colorScheme = DarkGreenColorScheme) {
                val context = LocalContext.current
                MainScreen(
                    context = context,
                    onButtonClick = {
                        val intent = Intent(context, SecondActivity::class.java)
                        context.startActivity(intent)
                    }
                )
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello, $name ! \n" +
                "This is the login test",
        modifier = modifier
    )
}

@Composable
fun MainScreen(
    context: Context,
    onButtonClick: () -> Unit
) {
    val message1 = remember { mutableStateOf("") }
    val message2 = remember { mutableStateOf("") }
    val showDialog = remember { mutableStateOf(false) }
    val dialogText = remember { mutableStateOf("") }
    val passwordVisible = remember { mutableStateOf(false) }

    fun isValidLogin(login: String): Boolean {
        return login.isNotEmpty() && login.any { it.isLetter() } && login.all { it.isLetterOrDigit() }
    }

    fun isValidPassword(password: String): Boolean {
        return password.length >= 8
    }

    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Box(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Welcome to the Test App!",
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(bottom = 16.dp)
                )
                OutlinedTextField(
                    message1.value,
                    { message1.value = it },
                    textStyle = TextStyle(fontSize = 18.sp),
                    placeholder = { Text("Login") },
                    colors = TextFieldDefaults.colors(
                        unfocusedContainerColor = Color(0xff333333),
                        unfocusedTextColor = Color.White,
                        focusedContainerColor = Color(0xff444444),
                        focusedTextColor = Color.White,
                    ),
                    leadingIcon = { Icon(Icons.Filled.Check, contentDescription = "Checked") },
                    trailingIcon = { Icon(Icons.Filled.Info, contentDescription = "Enter your login") }
                )
                OutlinedTextField(
                    message2.value,
                    { message2.value = it },
                    textStyle = TextStyle(fontSize = 18.sp),
                    placeholder = { Text("Password") },
                    visualTransformation = if (passwordVisible.value) VisualTransformation.None else PasswordVisualTransformation(),
                    colors = TextFieldDefaults.colors(
                        unfocusedContainerColor = Color(0xff333333),
                        unfocusedTextColor = Color.White,
                        focusedContainerColor = Color(0xff444444),
                        focusedTextColor = Color.White,
                    ),
                    leadingIcon = { Icon(Icons.Filled.Check, contentDescription = "Checked") },
                    trailingIcon = {
                        IconButton(onClick = { passwordVisible.value = !passwordVisible.value }) {
                            Icon(
                                imageVector = if (passwordVisible.value) Icons.Filled.Search else Icons.Filled.Lock,
                                contentDescription = if (passwordVisible.value) "Hide password" else "Show password"
                            )
                        }
                    }
                )
                Button(
                    onClick = {
                        val loginValid = isValidLogin(message1.value)
                        val passwordValid = isValidPassword(message2.value)

                        if (loginValid && passwordValid) {
                            onButtonClick()
                        } else {
                            dialogText.value = when {
                                !loginValid && !passwordValid -> "Login must contain at least one letter and must not contain any special symbols. Password must contain at least 8 symbols."
                                !loginValid -> "Login must contain at least one letter and must not contain any special symbols."
                                !passwordValid -> "Password must contain at least 8 symbols."
                                else -> "Just exist. There is no error."
                            }
                            showDialog.value = true
                        }
                    },
                    modifier = Modifier
                        .width(400.dp)
                        .height(50.dp)
                ) {
                    Text(text = "Enter")
                }
            }
        }

        if (showDialog.value) {
            AlertDialog(
                onDismissRequest = { showDialog.value = false },
                title = { Text("Error") },
                text = { Text(dialogText.value) },
                confirmButton = {
                    TextButton(onClick = { showDialog.value = false }) {
                        Text("OK")
                    }
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MaterialThemeB(colorScheme = DarkGreenColorScheme) {
        Greeting("Android")
    }
}
