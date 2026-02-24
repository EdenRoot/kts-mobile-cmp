package dev.kiryao.ktsmobilecmp.auth.login.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ktsmobilecmp.composeapp.generated.resources.Res
import ktsmobilecmp.composeapp.generated.resources.continue_button
import ktsmobilecmp.composeapp.generated.resources.email_or_phone
import ktsmobilecmp.composeapp.generated.resources.enter_your_email
import ktsmobilecmp.composeapp.generated.resources.ic_close
import ktsmobilecmp.composeapp.generated.resources.ic_mail
import ktsmobilecmp.composeapp.generated.resources.wrong_email
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun EntryCard(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
    isEmailWrong: Boolean = false,
) {
    var textMail by rememberSaveable { mutableStateOf("") }
    val mainGreen = Color(0xFF12B956)
    val darkCard = Color(0xFF1E1E1E)

    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 4.dp),
        shape = RoundedCornerShape(28.dp),
        border = BorderStroke(1.dp, Color.White.copy(alpha = 0.1f)),
        colors = CardDefaults.cardColors(containerColor = darkCard)
    ) {
        Column(
            modifier = Modifier
                .padding(24.dp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            Text(
                text = stringResource(Res.string.enter_your_email),
                style = MaterialTheme.typography.titleMedium.copy(
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 0.5.sp
                )
            )

            OutlinedTextField(
                value = textMail,
                onValueChange = { textMail = it },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                singleLine = true,
                isError = isEmailWrong,
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = Color.Black.copy(alpha = 0.2f),
                    unfocusedContainerColor = Color.Black.copy(alpha = 0.2f),
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White,
                    focusedBorderColor = mainGreen,
                    unfocusedBorderColor = Color.White.copy(alpha = 0.1f),
                    errorBorderColor = Color(0xFFEF5350),
                    cursorColor = mainGreen
                ),
                leadingIcon = {
                    Icon(
                        painter = painterResource(Res.drawable.ic_mail),
                        contentDescription = null,
                        tint = if (isEmailWrong) Color(0xFFEF5350) else Color.Gray,
                        modifier = Modifier.size(20.dp)
                    )
                },
                placeholder = {
                    Text(
                        text = if (isEmailWrong) stringResource(Res.string.wrong_email)
                        else stringResource(Res.string.email_or_phone),
                        color = if (isEmailWrong) Color(0xFFEF5350).copy(alpha = 0.7f)
                        else Color.Gray,
                        style = MaterialTheme.typography.bodyMedium
                    )
                },
                trailingIcon = {
                    if (textMail.isNotEmpty()) {
                        IconButton(onClick = { textMail = "" }) {
                            Icon(
                                painter = painterResource(Res.drawable.ic_close),
                                contentDescription = null,
                                tint = Color.White.copy(alpha = 0.5f),
                                modifier = Modifier.size(18.dp)
                            )
                        }
                    }
                }
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Button(
                    onClick = onClick,
                    enabled = textMail.isNotEmpty(),
                    modifier = Modifier
                        .weight(1.4f)
                        .height(54.dp),
                    shape = RoundedCornerShape(16.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = mainGreen,
                        contentColor = Color.White,
                        disabledContainerColor = mainGreen.copy(alpha = 0.2f),
                        disabledContentColor = Color.White.copy(alpha = 0.3f)
                    ),
                    elevation = ButtonDefaults.buttonElevation(defaultElevation = 4.dp)
                ) {
                    Text(
                        text = stringResource(Res.string.continue_button),
                        style = MaterialTheme.typography.labelLarge.copy(
                            fontWeight = FontWeight.ExtraBold,
                            fontSize = 16.sp
                        )
                    )
                }
            }
        }
    }
}