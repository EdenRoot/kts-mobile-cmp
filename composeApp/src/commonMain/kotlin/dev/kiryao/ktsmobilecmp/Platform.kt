package dev.kiryao.ktsmobilecmp

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform