package com.example.vivekdemo

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform