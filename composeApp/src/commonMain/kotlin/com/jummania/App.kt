package com.jummania

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.Font
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App(font: Font) {
    MaterialTheme {
        ChessBoardCanvas(font)
    }
}