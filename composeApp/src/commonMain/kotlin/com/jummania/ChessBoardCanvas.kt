package com.jummania

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp


/**
 * Created by Jummania on 23/4/25.
 * Email: sharifuddinjumman@gmail.com
 * Dhaka, Bangladesh.
 */

@Composable
fun ChessBoardCanvas(font: Font) {
    var touchedX: Float = 0f
    var touchedY: Float = 0f

    var selectedRowNumber = -1
    var isSelected: Boolean = false
    var isInvalidate: Boolean = false
    var enableStroke: Boolean = true

    var arkSquareColor = Color(0xFF8E4F19)
    var lightSquareColor: Color = Color(0xFFFADEAF)
    val darkSquareColor: Color = Color(0xFF8E4F19)

    val chessFont: FontFamily = FontFamily(font)

    // Convert padding to pixels
    //  val paddingPx = with(LocalDensity.current) { boardPadding.toPx() }

    // Paints for text drawing
    val symbolPaint = Paint()
    val paint = Paint()


    val chessController = ChessController(true, true, Color.White, Color.Black) {

    }

    fun swapTo(fromIndex: Int, toIndex: Int, isOnline: Boolean): Boolean {
        val isSwapped = chessController.swapTo(fromIndex, toIndex, isOnline)
        if (isSwapped) {
            isSelected = false
            selectedRowNumber = -1
            isInvalidate = true
            // invalidate()
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


    // Create a text measurer
    val textMeasurer = rememberTextMeasurer()

    Canvas(modifier = Modifier.fillMaxSize()) {
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
                if (touchedX in left..right && touchedY in top..bottom) {
                    touchedInside = handleTouch(rowIndex)  // Handle the touch (selection or move)
                }

                // Draw the current square with the appropriate color
                drawRect(
                    color = if (isDarkSquare) darkSquareColor else lightSquareColor,
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
                        style = Stroke(width = padding)
                    )
                }

                // Draw the chess piece in the current square, if one exists
                val piece = chessController.get(rowIndex)
                if (piece != null) {

                    val textSize = squareSize * 0.4f // Symbol size as a percentage of square size
                    val centerX = left + textSize / 2
                    val symbol = piece.symbol
                    val isLightPiece = chessController.isLightPiece(piece)
                    val strokeLightColor = Color.LightGray // Use your color here
                    val strokeDarkColor = Color.DarkGray // Use your color here

                    // Create a text style for drawing the symbol
                    val textStyle = TextStyle(
                        color = piece.color,
                        fontSize = textSize.sp,
                        fontWeight = FontWeight.Normal,
                        textAlign = TextAlign.Center,
                        fontFamily = chessFont
                    )


                    // Measure the text (symbol) to get TextLayoutResult
                    val textLayoutResult: TextLayoutResult = textMeasurer.measure(
                        text = symbol, style = textStyle
                    )

                    // If stroke effect is enabled
                    if (enableStroke) {
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
                                brush = SolidColor(if (isLightPiece) strokeLightColor else strokeDarkColor),
                                topLeft = Offset(centerX, top)
                            )
                        }

                        // Draw the actual piece symbol in the appropriate color
                        drawText(
                            textLayoutResult = textLayoutResult,
                            brush = SolidColor(piece.color),
                            topLeft = Offset(centerX, top)
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
                                brush = SolidColor(if (isLightPiece) strokeLightColor else strokeDarkColor),
                                topLeft = Offset(centerX, top)
                            )
                        }
                    } else {
                        // If stroke is disabled, just draw the piece symbol
                        drawText(
                            textLayoutResult = textLayoutResult,
                            brush = SolidColor(piece.color),
                            topLeft = Offset(centerX, top)
                        )
                    }
                }

                // Alternate the color of the squares for the next column
                isDarkSquare = !isDarkSquare
            }
        }
    }
}
