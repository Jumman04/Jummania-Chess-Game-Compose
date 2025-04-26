package com.jummania

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.Font
import com.jummania.utils.SymbolStyle

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform

@Composable
expect fun getFont(symbolStyle: SymbolStyle): Font