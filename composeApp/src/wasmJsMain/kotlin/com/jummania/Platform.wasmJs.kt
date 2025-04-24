package com.jummania

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import jummaniachessgamecompose.composeapp.generated.resources.Res
import jummaniachessgamecompose.composeapp.generated.resources.chess_alpha
import jummaniachessgamecompose.composeapp.generated.resources.chess_merida_unicode
import jummaniachessgamecompose.composeapp.generated.resources.symbola
import org.jetbrains.compose.resources.Font
import org.jetbrains.compose.resources.FontResource

class WasmPlatform : Platform {
    override val name: String = "Web with Kotlin/Wasm"
}

actual fun getPlatform(): Platform = WasmPlatform()

@Composable
actual fun getFont(symbolStyle: SymbolStyle, useBoldSymbol: Boolean): Font {

    @Composable
    fun getFont(resInt: FontResource): Font {
        return Font(
            resInt, if (useBoldSymbol) FontWeight.Bold else FontWeight.Normal
        )
    }

    return when (symbolStyle) {
        SymbolStyle.CLASSIC -> getFont(Res.font.chess_alpha)
        SymbolStyle.MERIDA -> getFont(Res.font.chess_merida_unicode)
        else -> getFont(Res.font.symbola)
    }
}