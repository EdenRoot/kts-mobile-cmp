package dev.kiryao.ktsmobilecmp.auth.welcome

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import dev.kiryao.ktsmobilecmp.auth.welcome.components.WelcomeContent
import dev.kiryao.ktsmobilecmp.auth.welcome.components.WelcomeContinueButton
import dev.kiryao.ktsmobilecmp.auth.welcome.components.WelcomeRow
import dev.kiryao.ktsmobilecmp.auth.welcome.data.WelcomeMockDataSource.row1
import dev.kiryao.ktsmobilecmp.auth.welcome.data.WelcomeMockDataSource.row2
import dev.kiryao.ktsmobilecmp.auth.welcome.data.WelcomeMockDataSource.row3
import dev.kiryao.ktsmobilecmp.auth.welcome.data.WelcomeMockDataSource.row4
import dev.kiryao.ktsmobilecmp.auth.welcome.data.WelcomeMockDataSource.row5
import dev.kiryao.ktsmobilecmp.auth.welcome.model.CourseName
import dev.kiryao.ktsmobilecmp.ui.theme.KTSMobileTheme
import ktsmobilecmp.composeapp.generated.resources.Res
import ktsmobilecmp.composeapp.generated.resources.course_kotlin_development

@Composable
fun WelcomeScreen(
    onNavigateToLogin: () -> Unit,
    modifier: Modifier = Modifier
) {
    val rows = listOf(row1, row2, row3, row4, row5)

    Surface(
        modifier = modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Box(
            modifier = modifier
                .background(color = MaterialTheme.colorScheme.background)
                .fillMaxSize()
        ) {
            WelcomeContent(
                rows = rows,
                modifier = Modifier.align(Alignment.Center)
            )
            WelcomeContinueButton(
                onNavigateToLogin = onNavigateToLogin,
                modifier = Modifier.align(Alignment.BottomCenter)
            )
        }
    }
}

@Preview(showBackground = true, name = "Welcome Screen Light")
@Preview(showBackground = true, name = "Welcome Screen Dark", uiMode = 32)
@Composable
fun WelcomeScreenPreview() {
    KTSMobileTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            WelcomeScreen(onNavigateToLogin = {})
        }
    }
}