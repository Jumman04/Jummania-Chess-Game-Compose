package com.jummania

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.Font
import com.jummania.utils.SymbolStyle
import jummaniachessgamecompose.composeapp.generated.resources.Res
import jummaniachessgamecompose.composeapp.generated.resources.chess_alpha
import jummaniachessgamecompose.composeapp.generated.resources.chess_merida_unicode
import jummaniachessgamecompose.composeapp.generated.resources.symbola
import org.jetbrains.compose.resources.Font

@Composable
actual fun getFont(symbolStyle: SymbolStyle): Font {
    return when (symbolStyle) {
        SymbolStyle.CLASSIC -> Font(Res.font.chess_alpha)
        SymbolStyle.MERIDA -> Font(Res.font.chess_merida_unicode)
        else -> Font(Res.font.symbola)
    }
}