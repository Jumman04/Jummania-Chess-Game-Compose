package com.jummania


/**
 * Handles the castling state of a player in a chess game.
 *
 * Keeps track of whether the king or rooks have moved,
 * to determine if castling (king-side or queen-side) is still allowed.
 *
 * Created by Jummania on 17/04/2025
 * Email: sharifuddinjumman@gmail.com
 * Dhaka, Bangladesh
 */
class Castling {

    private var kingMoved = false
    private var firstRookMoved = false  // Typically the rook on queen-side (a-file)
    private var secondRookMoved = false // Typically the rook on king-side (h-file)

    /** Marks the king as moved. */
    fun markKingMoved() {
        kingMoved = true
    }

    /** Marks the queen-side rook as moved. */
    fun markFirstRookMoved() {
        firstRookMoved = true
    }

    /** Marks the king-side rook as moved. */
    fun markSecondRookMoved() {
        secondRookMoved = true
    }

    /** Returns true if king-side castling is still possible. */
    fun isKingSideCastlingPossible(): Boolean {
        return !kingMoved && !secondRookMoved
    }

    /** Returns true if queen-side castling is still possible. */
    fun isQueenSideCastlingPossible(): Boolean {
        return !kingMoved && !firstRookMoved
    }

    /** Marks all castling options as no longer available. */
    fun markCastled() {
        kingMoved = true
        firstRookMoved = true
        secondRookMoved = true
    }
}