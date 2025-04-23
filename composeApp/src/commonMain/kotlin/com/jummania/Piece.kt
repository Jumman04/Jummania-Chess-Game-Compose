package com.jummania

import androidx.compose.ui.graphics.Color


/**
 * Represents a chess piece using its symbol and color.
 *
 * @property symbol The Unicode symbol representing the piece (e.g., ♔, ♞).
 * @property color The color of the piece (e.g., 0 for white, 1 for black).
 *
 * Created by Jummania on 28/03/2025
 * Email: sharifuddinjumman@gmail.com
 * Dhaka, Bangladesh
 */
data class Piece(
    var symbol: String, val color: Color
) {

    /** Returns true if the piece is a King. */
    fun isKing(): Boolean {
        return symbol == "♔" || symbol == "♚"
    }

    /** Returns true if the piece is a Queen. */
    fun isQueen(): Boolean {
        return symbol == "♕" || symbol == "♛"
    }

    /** Returns true if the piece is a Rook. */
    fun isRook(): Boolean {
        return symbol == "♖" || symbol == "♜"
    }

    /** Returns true if the piece is a Bishop. */
    fun isBishop(): Boolean {
        return symbol == "♗" || symbol == "♝"
    }

    /** Returns true if the piece is a Knight. */
    fun isKnight(): Boolean {
        return symbol == "♘" || symbol == "♞"
    }

    /** Returns true if the piece is a Pawn. */
    fun isPawn(): Boolean {
        return symbol == "♙" || symbol == "♟"
    }
}