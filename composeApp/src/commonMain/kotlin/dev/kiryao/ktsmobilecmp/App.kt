package dev.kiryao.ktsmobilecmp

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dev.kiryao.ktsmobilecmp.auth.login.LoginScreen
import dev.kiryao.ktsmobilecmp.auth.welcome.WelcomeScreen

enum class Screen {
    Welcome,
    Login
}

@Composable
@Preview
fun App() {
    MaterialTheme {
        val navController = rememberNavController()

        NavHost(
            navController = navController,
            startDestination = Screen.Welcome.name
        ) {
            composable(route = Screen.Welcome.name) {
                WelcomeScreen(
                    onNavigateToLogin = {
                        navController.navigate(Screen.Login.name)
                    }
                )
            }
            composable(route = Screen.Login.name) {
                LoginScreen()
            }
        }
    }
}