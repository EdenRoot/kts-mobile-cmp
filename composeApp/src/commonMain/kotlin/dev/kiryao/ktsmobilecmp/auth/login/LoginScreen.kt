package dev.kiryao.ktsmobilecmp.auth.login

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.kiryao.ktsmobilecmp.auth.login.components.EntryCard
import dev.kiryao.ktsmobilecmp.auth.login.components.VerificationCodeScreen
import dev.kiryao.ktsmobilecmp.ui.theme.KTSMobileTheme
import ktsmobilecmp.composeapp.generated.resources.Res
import ktsmobilecmp.composeapp.generated.resources.title_log_in_profile
import org.jetbrains.compose.resources.stringResource

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier
) {
    var isEmailVerify by rememberSaveable { mutableStateOf(false) }

    Surface(
        modifier = modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier
                .fillMaxSize()
                .statusBarsPadding()
                .padding(16.dp)
        ) {
            if (!isEmailVerify) {
                Text(
                    text = stringResource(Res.string.title_log_in_profile),
                    style = MaterialTheme.typography.titleLarge.copy(
                        color = MaterialTheme.colorScheme.onBackground,
                        fontWeight = FontWeight.Bold
                    ),
                    modifier = Modifier
                        .align(Alignment.Start)
                        .padding(top = 16.dp, bottom = 16.dp)
                )

                Spacer(modifier = Modifier.weight(0.3f))

                EntryCard(
                    onClick = {
                        isEmailVerify = true
                    }
                )

                Spacer(modifier = Modifier.weight(1f))

            } else {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    VerificationCodeScreen(
                        onClick = {
                        }
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, name = "Verification Code Light")
@Preview(showBackground = true, name = "Verification Code Dark", uiMode = 32)
@Composable
fun LoginScreenPreview() {
    KTSMobileTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            LoginScreen()
        }
    }
}