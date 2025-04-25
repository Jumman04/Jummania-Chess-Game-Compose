package com.jummania.model

import androidx.compose.ui.graphics.Color

data class Stroke(
    val enabled: Boolean = true,
    val lightColor: Color = Color.LightGray,
    val darkColor: Color = Color.DarkGray,
)
