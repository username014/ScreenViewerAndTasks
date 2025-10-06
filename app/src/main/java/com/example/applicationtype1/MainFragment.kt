package com.example.applicationtype1

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Lock
import androidx.lifecycle.viewmodel.compose.viewModel

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

class MainFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                MaterialTheme(colorScheme = DarkGreenColorScheme) {
                    MainScreen(
                        onButtonClick = {
                            parentFragmentManager.commit {
                                replace(R.id.fragment_container, SecondFragment())
                                addToBackStack(null)
                            }
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun MainScreen(onButtonClick: () -> Unit) {
    val viewModel: LoginViewModel = viewModel()
    val username by viewModel.username.collectAsState()
    val password by viewModel.password.collectAsState()
    val passwordVisible by viewModel.passwordVisible.collectAsState()
    val errorMessage by viewModel.errorMessage.collectAsState()
    val showDialog = remember { mutableStateOf(false) }

    LaunchedEffect(errorMessage) {
        showDialog.value = errorMessage.isNotEmpty()
    }

    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Box(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = "Welcome to the Test App!",
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(bottom = 16.dp)
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = username,
                    onValueChange = viewModel::updateUsername,
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
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = password,
                    onValueChange = viewModel::updatePassword,
                    textStyle = TextStyle(fontSize = 18.sp),
                    placeholder = { Text("Password") },
                    visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                    colors = TextFieldDefaults.colors(
                        unfocusedContainerColor = Color(0xff333333),
                        unfocusedTextColor = Color.White,
                        focusedContainerColor = Color(0xff444444),
                        focusedTextColor = Color.White,
                    ),
                    leadingIcon = { Icon(Icons.Filled.Check, contentDescription = "Checked") },
                    trailingIcon = {
                        IconButton(onClick = viewModel::togglePasswordVisibility) {
                            Icon(
                                imageVector = if (passwordVisible) Icons.Filled.Search else Icons.Filled.Lock,
                                contentDescription = if (passwordVisible) "Hide password" else "Show password"
                            )
                        }
                    }
                )
                Spacer(modifier = Modifier.height(8.dp))
                Button(
                    onClick = {
                        viewModel.validateAndLogin(onSuccess = onButtonClick)
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
                onDismissRequest = { showDialog.value = false
                    viewModel.clearErrorMessage()
                    },
                title = { Text("Error") },
                text = { Text(errorMessage) },
                confirmButton = {
                    TextButton(onClick = {
                        showDialog.value = false
                        viewModel.clearErrorMessage()
                    }) {
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
    MaterialTheme(colorScheme = DarkGreenColorScheme) {
        Greeting("Android")
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
