package dev.kiryao.ktsmobilecmp

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dev.kiryao.ktsmobilecmp.auth.login.LoginScreen
import dev.kiryao.ktsmobilecmp.auth.verification.VerificationCodeScreen
import dev.kiryao.ktsmobilecmp.auth.welcome.WelcomeScreen
import dev.kiryao.ktsmobilecmp.ui.theme.KTSMobileTheme

enum class Screen {
    Welcome,
    Login,
    Verification
}

@Composable
@Preview
fun App() {
    KTSMobileTheme {
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
                LoginScreen(
                    onNavigateToVerification = {
                        navController.navigate(Screen.Verification.name)
                    }
                )
            }
            composable(route = Screen.Verification.name) {
                VerificationCodeScreen({})
            }
        }
    }
}