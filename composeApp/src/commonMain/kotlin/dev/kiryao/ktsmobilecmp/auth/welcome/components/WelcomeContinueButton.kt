package dev.kiryao.ktsmobilecmp.auth.welcome.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.kiryao.ktsmobilecmp.ui.theme.KTSMobileTheme
import ktsmobilecmp.composeapp.generated.resources.Res
import ktsmobilecmp.composeapp.generated.resources.continue_button
import org.jetbrains.compose.resources.stringResource

@Composable
fun WelcomeContinueButton(
    onNavigateToLogin: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onNavigateToLogin,
        modifier = modifier
            .padding(top = 16.dp, bottom = 32.dp, start = 16.dp, end = 16.dp)
            .fillMaxWidth()
            .height(56.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.onPrimary
        )
    ) {
        Text(
            stringResource(Res.string.continue_button),
            style = MaterialTheme.typography.labelLarge,
            color = MaterialTheme.colorScheme.onBackground
        )
    }
}

@Preview(showBackground = true, name = "WelcomeContinueButton Light")
@Preview(showBackground = true, name = "WelcomeContinueButton Dark", uiMode = 32)
@Composable
fun WelcomeContinueButtonPreview() {
    KTSMobileTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            WelcomeContinueButton(onNavigateToLogin = {})
        }
    }
}