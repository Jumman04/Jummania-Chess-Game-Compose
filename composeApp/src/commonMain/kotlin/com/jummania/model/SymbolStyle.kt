package com.jummania.model

import com.jummania.SymbolStyle

data class SymbolStyle(
    val style: SymbolStyle = SymbolStyle.SYMBOLA,
    val useBoldSymbol: Boolean = false
)
