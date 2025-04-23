package com.jummania

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "Jummania Chess Game Compose",
    ) {
        App()
    }
}