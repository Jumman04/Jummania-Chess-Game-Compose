package com.jummania.model

import androidx.compose.ui.graphics.Color

data class Stroke(
    val enableStroke: Boolean = true,
    val strokeLightColor: Color = Color.LightGray,
    val strokeDarkColor: Color = Color.DarkGray,
)
