package com.jummania

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.Font

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform

@Composable
expect fun getFont(symbolStyle: SymbolStyle, useBoldSymbol: Boolean): Font