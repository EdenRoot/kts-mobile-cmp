package dev.kiryao.ktsmobilecmp.auth.welcome.data

import dev.kiryao.ktsmobilecmp.auth.welcome.model.CourseName
import ktsmobilecmp.composeapp.generated.resources.Res
import ktsmobilecmp.composeapp.generated.resources.course_b2b_marketing
import ktsmobilecmp.composeapp.generated.resources.course_big_data
import ktsmobilecmp.composeapp.generated.resources.course_cinema_4d
import ktsmobilecmp.composeapp.generated.resources.course_content_marketing
import ktsmobilecmp.composeapp.generated.resources.course_google_analytics
import ktsmobilecmp.composeapp.generated.resources.course_kotlin_development
import ktsmobilecmp.composeapp.generated.resources.course_parsing
import ktsmobilecmp.composeapp.generated.resources.course_prompt_engineering
import ktsmobilecmp.composeapp.generated.resources.course_python_development
import ktsmobilecmp.composeapp.generated.resources.course_rabbitmq
import ktsmobilecmp.composeapp.generated.resources.course_ui_design
import ktsmobilecmp.composeapp.generated.resources.course_ux_researcher
import ktsmobilecmp.composeapp.generated.resources.course_web_analytics
import ktsmobilecmp.composeapp.generated.resources.course_web_design

object WelcomeMockDataSource {
    val row1 = listOf(
        CourseName(text = Res.string.course_kotlin_development),
        CourseName(text = Res.string.course_b2b_marketing),
        CourseName(text = Res.string.course_content_marketing, highlighted = true),
        CourseName(text = Res.string.course_rabbitmq),
        CourseName(text = Res.string.course_cinema_4d),
    )
    val row2 = listOf(
        CourseName(text = Res.string.course_b2b_marketing),
        CourseName(text = Res.string.course_content_marketing),
        CourseName(text = Res.string.course_rabbitmq),
        CourseName(text = Res.string.course_cinema_4d),
        CourseName(text = Res.string.course_python_development),
    )
    val row3 = listOf(
        CourseName(text = Res.string.course_big_data),
        CourseName(text = Res.string.course_parsing),
        CourseName(text = Res.string.course_google_analytics, highlighted = true),
        CourseName(text = Res.string.course_ux_researcher),
        CourseName(text = Res.string.course_web_analytics),
    )
    val row4 = listOf(
        CourseName(text = Res.string.course_web_analytics),
        CourseName(text = Res.string.course_ui_design),
        CourseName(text = Res.string.course_web_design),
        CourseName(text = Res.string.course_prompt_engineering),
        CourseName(text = Res.string.course_ux_researcher),
    )
    val row5 = listOf(
        CourseName(text = Res.string.course_parsing),
        CourseName(text = Res.string.course_google_analytics),
        CourseName(text = Res.string.course_ux_researcher),
        CourseName(text = Res.string.course_web_analytics, highlighted = true),
        CourseName(text = Res.string.course_ui_design),
    )
}