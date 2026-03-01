package dev.kiryao.ktsmobilecmp.auth.welcome.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.kiryao.ktsmobilecmp.auth.welcome.model.CourseName
import dev.kiryao.ktsmobilecmp.ui.theme.KTSMobileTheme
import ktsmobilecmp.composeapp.generated.resources.Res
import ktsmobilecmp.composeapp.generated.resources.course_content_marketing
import org.jetbrains.compose.resources.stringResource

@Composable
fun CourseCard(
    courseName: CourseName
) {
    val rotation = if (courseName.highlighted) -15f else 0f

    val backgroundColor = if (courseName.highlighted) {
        MaterialTheme.colorScheme.primary
    } else {
        MaterialTheme.colorScheme.surfaceVariant
    }

    val contentColor = if (courseName.highlighted) {
        MaterialTheme.colorScheme.onPrimary
    } else {
        MaterialTheme.colorScheme.onSurface
    }

    val elevation = if (courseName.highlighted) 12.dp else 4.dp

    Card(
        shape = RoundedCornerShape(50),
        modifier = Modifier
            .padding(3.dp)
            .height(40.dp)
            .wrapContentWidth()
            .graphicsLayer { rotationZ = rotation }
            .then(Modifier),
        elevation = CardDefaults.cardElevation(elevation),
        colors = CardDefaults.cardColors(
            containerColor = backgroundColor,
            contentColor = contentColor
        )
    ) {
        Box(
            modifier = Modifier
                .padding(horizontal = 12.dp)
                .fillMaxHeight(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = stringResource(courseName.text),
                color = MaterialTheme.colorScheme.onBackground,
                fontSize = 12.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@Preview(showBackground = true, name = "CourseCard Light")
@Preview(showBackground = true, name = "CourseCard Dark", uiMode = 32)
@Composable
fun CourseCardHighlightedPreview() {
    KTSMobileTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            CourseCard(
                CourseName(text = Res.string.course_content_marketing, highlighted = true),
            )
        }
    }
}

@Preview(showBackground = true, name = "CourseCard Light")
@Preview(showBackground = true, name = "CourseCard Dark", uiMode = 32)
@Composable
fun CourseCardNormalPreview() {
    KTSMobileTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            CourseCard(CourseName(text = Res.string.course_content_marketing, highlighted = false))
        }
    }
}