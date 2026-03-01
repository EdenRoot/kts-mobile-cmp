package dev.kiryao.ktsmobilecmp.auth.verification

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.kiryao.ktsmobilecmp.ui.theme.KTSMobileTheme
import kotlinx.coroutines.delay
import ktsmobilecmp.composeapp.generated.resources.Res
import ktsmobilecmp.composeapp.generated.resources.confirm_button
import ktsmobilecmp.composeapp.generated.resources.email_code_description
import ktsmobilecmp.composeapp.generated.resources.send_an_email_message
import ktsmobilecmp.composeapp.generated.resources.send_verification_code_again
import org.jetbrains.compose.resources.stringResource

@Composable
fun VerificationCodeScreen(
    onConfirm: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var code by remember { mutableStateOf("") }
    val focusRequester = remember { FocusRequester() }
    val focusManager = LocalFocusManager.current

    Surface(
        modifier = modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(24.dp),
                modifier = modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.background)
                    .padding(horizontal = 16.dp)
            ) {
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Text(
                        text = stringResource(Res.string.send_an_email_message),
                        style = MaterialTheme.typography.headlineSmall.copy(
                            color = MaterialTheme.colorScheme.onBackground,
                            fontWeight = FontWeight.Bold
                        )
                    )
                    Text(
                        text = stringResource(Res.string.email_code_description),
                        style = MaterialTheme.typography.bodyMedium.copy(
                            color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.7f),
                            lineHeight = 20.sp
                        )
                    )
                }

                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        repeat(4) { index ->
                            val char = code.getOrNull(index)?.toString().orEmpty()
                            val isFocused = code.length == index

                            Box(
                                modifier = Modifier
                                    .size(70.dp)
                                    .clip(RoundedCornerShape(16.dp))
                                    .background(MaterialTheme.colorScheme.surfaceVariant)
                                    .border(
                                        width = 2.dp,
                                        color =
                                            if (isFocused) MaterialTheme.colorScheme.primary
                                            else MaterialTheme.colorScheme.outline.copy(alpha = 0.1f),
                                        shape = RoundedCornerShape(16.dp)
                                    ),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = char,
                                    style = MaterialTheme.typography.headlineMedium.copy(
                                        color = MaterialTheme.colorScheme.onSurface,
                                        fontWeight = FontWeight.Bold
                                    )
                                )
                                if (char.isEmpty()) {
                                    Box(
                                        modifier = Modifier
                                            .size(8.dp)
                                            .background(
                                                MaterialTheme.colorScheme.onSurfaceVariant.copy(0.3f),
                                                CircleShape
                                            )
                                    )
                                }
                            }
                        }
                    }

                    BasicTextField(
                        value = code,
                        onValueChange = {
                            if (it.length <= 4) {
                                code = it
                                if (it.length == 4) focusManager.clearFocus()
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(70.dp)
                            .focusRequester(focusRequester)
                            .alpha(0.01f),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword)
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                Button(
                    onClick = { onConfirm(code) },
                    enabled = code.length == 4,
                    shape = RoundedCornerShape(16.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        contentColor = MaterialTheme.colorScheme.onPrimary,
                        disabledContainerColor = MaterialTheme.colorScheme.primary.copy(0.2f),
                        disabledContentColor = MaterialTheme.colorScheme.onPrimary.copy(0.3f)
                    )
                ) {
                    Text(
                        text = stringResource(Res.string.confirm_button),
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontWeight = FontWeight.Bold
                        )
                    )
                }

                TextButton(
                    onClick = { },
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                ) {
                    Text(
                        text = stringResource(Res.string.send_verification_code_again),
                        color = MaterialTheme.colorScheme.primary,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
    LaunchedEffect(Unit) {
        delay(100)
        focusRequester.requestFocus()
    }
}

@Preview(showBackground = true, name = "Verification Code Light")
@Preview(showBackground = true, name = "Verification Code Dark", uiMode = 32)
@Composable
fun VerificationCodeScreenPreview() {
    KTSMobileTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            VerificationCodeScreen({})
        }
    }
}