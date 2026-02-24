package dev.kiryao.ktsmobilecmp.auth.login.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ktsmobilecmp.composeapp.generated.resources.Res
import ktsmobilecmp.composeapp.generated.resources.confirm_button
import ktsmobilecmp.composeapp.generated.resources.email_code_description
import ktsmobilecmp.composeapp.generated.resources.send_an_email_message
import org.jetbrains.compose.resources.stringResource

@Composable
fun VerificationCodeScreen(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val mainGreen = Color(0xFF12B956)
    val darkBg = Color(0xFF151515)

    var code by remember { mutableStateOf("") }

    Column(
        verticalArrangement = Arrangement.spacedBy(24.dp),
        modifier = modifier
            .fillMaxWidth()
            .background(darkBg)
            .padding(horizontal = 16.dp)
    ) {
        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Text(
                text = stringResource(Res.string.send_an_email_message),
                style = MaterialTheme.typography.headlineSmall.copy(
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
            )
            Text(
                text = stringResource(Res.string.email_code_description),
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = Color.Gray,
                    lineHeight = 20.sp
                )
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            repeat(4) { index ->
                val char = code.getOrNull(index)?.toString() ?: ""
                val isFocused = code.length == index

                Box(
                    modifier = Modifier
                        .size(width = 70.dp, height = 70.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .background(Color(0xFF1E1E1E))
                        .border(
                            width = 2.dp,
                            color = if (isFocused) mainGreen else Color.White.copy(alpha = 0.05f),
                            shape = RoundedCornerShape(16.dp)
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = char,
                        style = MaterialTheme.typography.headlineMedium.copy(
                            color = Color.White,
                            fontWeight = FontWeight.Bold
                        )
                    )
                    if (char.isEmpty()) {
                        Box(
                            modifier = Modifier
                                .size(8.dp)
                                .background(Color.Gray.copy(alpha = 0.3f), CircleShape)
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = onClick,
            enabled = code.length == 4,
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = mainGreen,
                disabledContainerColor = mainGreen.copy(alpha = 0.2f)
            )
        ) {
            Text(
                text = stringResource(Res.string.confirm_button),
                style = MaterialTheme.typography.titleMedium.copy(
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
            )
        }

        TextButton(
            onClick = { },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text(
                text = "Отправить код повторно",
                color = mainGreen,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}