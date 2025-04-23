package com.jummania

import androidx.compose.ui.text.platform.Font
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application


fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "Jummania Chess Game Compose",
    ) {
        App(Font("fonts/chess_font.ttf"))
    }
}