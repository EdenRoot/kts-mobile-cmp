package dev.kiryao.ktsmobilecmp.auth.welcome.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.kiryao.ktsmobilecmp.auth.welcome.model.CourseName
import dev.kiryao.ktsmobilecmp.ui.theme.KTSMobileTheme
import ktsmobilecmp.composeapp.generated.resources.Res
import ktsmobilecmp.composeapp.generated.resources.course_kotlin_development
import ktsmobilecmp.composeapp.generated.resources.welcome_text
import org.jetbrains.compose.resources.stringResource

@Composable
fun WelcomeContent(
    rows: List<List<CourseName>>,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(Res.string.welcome_text),
            style = MaterialTheme.typography.headlineSmall,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier.padding(bottom = 24.dp)
        )
        rows.forEachIndexed { index, row ->
            val reverse = index % 2 == 1
            WelcomeRow(list = row, reverse = reverse)
        }
    }
}

@Preview(showBackground = true, name = "WelcomeContent Light")
@Preview(showBackground = true, name = "WelcomeContent Dark", uiMode = 32)
@Composable
fun WelcomeContentPreview() {
    val mockRows = listOf(
        listOf(
            CourseName(Res.string.course_kotlin_development, highlighted = false),
            CourseName(Res.string.course_kotlin_development, highlighted = true)
        ),
        listOf(
            CourseName(Res.string.course_kotlin_development, highlighted = true),
            CourseName(Res.string.course_kotlin_development, highlighted = false)
        ),
        listOf(
            CourseName(Res.string.course_kotlin_development, highlighted = false),
            CourseName(Res.string.course_kotlin_development, highlighted = true)
        ),
        listOf(
            CourseName(Res.string.course_kotlin_development, highlighted = true),
            CourseName(Res.string.course_kotlin_development, highlighted = false)
        ),
        listOf(
            CourseName(Res.string.course_kotlin_development, highlighted = false),
            CourseName(Res.string.course_kotlin_development, highlighted = true)
        )
    )

    KTSMobileTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            WelcomeContent(rows = mockRows)
        }
    }
}