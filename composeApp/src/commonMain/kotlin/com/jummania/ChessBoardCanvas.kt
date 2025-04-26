package com.jummania

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import com.jummania.model.SquareColors
import com.jummania.model.Stroke
import com.jummania.model.SymbolStyle
import com.jummania.utils.ChessController
import com.jummania.utils.ChessController.Companion.isWhiteTurn
import com.jummania.utils.SimpleDialog
import com.jummania.utils.SingleChoiceDialog
import com.jummania.utils.Toast
import com.jummania.utils.UserNotifier


/**
 * Created by Jummania on 23/4/25.
 * Email: sharifuddinjumman@gmail.com
 * Dhaka, Bangladesh.
 */

var isInvalidate: Boolean = false
var toIndex: Int = -1
val toast = Toast()

val defaultChessController = ChessController()

@Composable
fun ChessBoardCanvas(
    chessController: ChessController = defaultChessController,
    squareColors: SquareColors = SquareColors(),
    symbolStyle: SymbolStyle = SymbolStyle(),
    stroke: Stroke = Stroke()
) {

    var selectedRowNumber = -1
    var isSelected: Boolean = false

    val chessFont: FontFamily = FontFamily(getFont(symbolStyle.style, symbolStyle.useBold))
    // val bitmap = getBitmap()

    var clickPosition by remember { mutableStateOf(Offset.Zero) }

    fun invalidate() {
        isSelected = false
        isInvalidate = true
        selectedRowNumber = -1
        clickPosition = Offset.Zero
    }

    fun swapTo(fromIndex: Int, toIndex: Int, isOnline: Boolean): Boolean {
        val isSwapped = chessController.swapTo(fromIndex, toIndex, isOnline)

        if (isSwapped) {
            invalidate()
        } else if (isOnline) {
            chessController.sendData()
        }

        return isSwapped
    }

    fun handleTouch(rowNumber: Int): Boolean {
        return when {
            // Case 1: The same row was selected, and the piece is already selected
            selectedRowNumber == rowNumber && isSelected -> {
                // Deselect the piece
                isSelected = false
                selectedRowNumber = -1
                true
            }

            // Case 2: A piece is selected and the move is valid (the piece can be swapped)
            isSelected && swapTo(selectedRowNumber, rowNumber, false) -> true


            // Case 3: No invalidation is pending and no piece is selected
            !isInvalidate -> {
                // Select the new piece on the specified row
                isSelected = true
                selectedRowNumber = rowNumber
                true
            }

            // Default case: Touch is ignored if invalidation is pending
            else -> false
        }
    }

    val density = LocalDensity.current
    fun getFontSizeFromSquareSize(squareSize: Float): TextUnit {
        return with(density) { squareSize.toSp() }
    }


    // Create a text measurer
    val textMeasurer = rememberTextMeasurer()

    Canvas(
        modifier = Modifier.fillMaxSize().pointerInput(Unit) {
            detectTapGestures {
                isInvalidate = false
                clickPosition = it
            }
        }) {

        println("invalidate()")

        val width = size.width
        val height = size.height

        // Calculate the minimum size of the board to maintain a square shape, with padding
        val minSize = minOf(width, height) - 22
        val boardLeft = (width - minSize) / 2f  // Horizontal starting point for the board
        val boardTop = (height - minSize) / 2f  // Vertical starting point for the board
        val squareSize = minSize / 8f  // Size of each square on the chessboard

        var rowIndex = -1  // Initialize the row index
        var touchedInside = false  // Flag to check if the touch is inside a square

        for (row in 0 until 8) {
            val top = boardTop + row * squareSize  // Top coordinate of the current row
            val bottom = top + squareSize  // Bottom coordinate of the current row
            var isDarkSquare = row % 2 != 0  // Alternate square colors for each row

            for (col in 0 until 8) {
                val left = boardLeft + col * squareSize  // Left coordinate of the current column
                val right = left + squareSize  // Right coordinate of the current column

                rowIndex++

                // Check if the touch event is within the bounds of the current square
                if (clickPosition.x in left..right && clickPosition.y in top..bottom) {
                    touchedInside = handleTouch(rowIndex)  // Handle the touch (selection or move)
                }

                // Draw the current square with the appropriate color
                drawRect(
                    color = if (isDarkSquare) squareColors.darkColor else squareColors.lightColor,
                    topLeft = Offset(left, top),
                    size = Size(right - left, bottom - top)
                )

                // Highlight the selected square if this is the one selected by the user
                if (selectedRowNumber == rowIndex && touchedInside && isSelected) {
                    val padding = squareSize * 0.05f

                    drawRect(
                        color = Color.Red,
                        topLeft = Offset(left + padding, top + padding),
                        size = Size((right - left) - 2 * padding, (bottom - top) - 2 * padding),
                        style = androidx.compose.ui.graphics.drawscope.Stroke(width = padding)
                    )
                }

                // Draw the chess piece in the current square, if one exists
                val piece = chessController.get(rowIndex)
                if (piece != null) {

                    val textSize = squareSize * 0.7f // Symbol size as a percentage of square size
                    val centerX = left + textSize / 3
                    val centerY = top + textSize / 5
                    val symbol = piece.symbol
                    val isLightPiece = chessController.isLightPiece(piece)

                    // Create a text style for drawing the symbol
                    val textStyle = TextStyle(
                        color = piece.color,
                        fontSize = getFontSizeFromSquareSize(textSize),
                        fontWeight = if (symbolStyle.useBold) FontWeight.Bold else FontWeight.Normal,
                        textAlign = TextAlign.Center,
                        fontFamily = chessFont
                    )


                    // Measure the text (symbol) to get TextLayoutResult
                    val textLayoutResult: TextLayoutResult = textMeasurer.measure(
                        text = symbol, style = textStyle
                    )

                    // If stroke effect is enabled
                    if (stroke.enabled) {
                        // Determine whether to draw a background behind the symbol based on the piece's color
                        val shouldDrawBackground = if (isLightPiece) {
                            !chessController.isLightFilled
                        } else {
                            !chessController.isDarkFilled
                        }

                        val transformedSymbol: TextLayoutResult = textMeasurer.measure(
                            text = chessController.transform(symbol), style = textStyle
                        )

                        // Draw background (stroke layer) if needed
                        if (shouldDrawBackground) {
                            drawText(
                                textLayoutResult = transformedSymbol,
                                brush = SolidColor(if (isLightPiece) stroke.lightColor else stroke.darkColor),
                                topLeft = Offset(centerX, centerY)
                            )
                        }

                        // Draw the actual piece symbol in the appropriate color
                        drawText(
                            textLayoutResult = textLayoutResult,
                            brush = SolidColor(piece.color),
                            topLeft = Offset(centerX, centerY)
                        )

                        // Draw top layer of the symbol if the piece is filled
                        val shouldDrawTopLayer = if (isLightPiece) {
                            chessController.isLightFilled
                        } else {
                            chessController.isDarkFilled
                        }

                        if (shouldDrawTopLayer) {
                            drawText(
                                textLayoutResult = transformedSymbol,
                                brush = SolidColor(if (isLightPiece) stroke.lightColor else stroke.darkColor),
                                topLeft = Offset(centerX, centerY)
                            )
                        }
                    } else {
                        // If stroke is disabled, just draw the piece symbol
                        drawText(
                            textLayoutResult = textLayoutResult,
                            brush = SolidColor(piece.color),
                            topLeft = Offset(centerX, centerY)
                        )
                    }
                }

                // Alternate the color of the squares for the next column
                isDarkSquare = !isDarkSquare
            }
        }

        if (!touchedInside) isSelected = false

        drawRect(
            color = Color.Black,
            topLeft = Offset(boardLeft - 2.5f, boardTop - 2.5f),
            size = Size(minSize + 5f, minSize + 5f),
            style = androidx.compose.ui.graphics.drawscope.Stroke(width = 4f)
        )

        // Create a text style for drawing the symbol
        val textStyle = TextStyle(
            color = Color.Red,
            fontSize = getFontSizeFromSquareSize(22f),
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            fontFamily = chessFont
        )

        val textLayoutResult: TextLayoutResult = textMeasurer.measure(
            text = "Player ${if (isWhiteTurn) "X" else "Y"} Turn", style = textStyle
        )

        val textWidth = textLayoutResult.size.width

        drawText(
            textLayoutResult = textLayoutResult, brush = SolidColor(Color.Black), topLeft = Offset(
                if (isWhiteTurn) boardLeft - textWidth * 2 else minSize + boardLeft + textWidth,
                minSize / 2
            )
        )

    }

    toast.initialize()

    var showGameEndDialog by remember { mutableStateOf(false) }
    var showRevivePawnDialog by remember { mutableStateOf(false) }

    SimpleDialog(showGameEndDialog, "Game Over", "Do you want to play again?") {
        invalidate()
        chessController.reset(true)
        showGameEndDialog = false
    }

    SingleChoiceDialog(showRevivePawnDialog, chessController, "Revive Your Pawn") {
        chessController.revivePawn(toIndex, it)
        showRevivePawnDialog = false
    }


    chessController.registerNotifier(object : UserNotifier {
        override fun message(message: String) {
            toast.show(message)
        }

        override fun gameEndDialogue() {
            showGameEndDialog = true
        }

        override fun revivePawnDialog(position: Int) {
            toIndex = position
            showRevivePawnDialog = true
        }

        override fun afterRevival() {

        }
    })


}
