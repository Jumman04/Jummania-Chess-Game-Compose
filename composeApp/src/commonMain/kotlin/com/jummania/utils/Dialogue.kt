package com.jummania.utils

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.AlertDialog
import androidx.compose.material.RadioButton
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SimpleDialog(
    show: Boolean, title: String, description: String, onConfirm: () -> Unit
) {
    if (show) {
        AlertDialog(
            onDismissRequest = {},
            title = { Text(title) },
            text = { Text(description) },
            confirmButton = {
                TextButton(onClick = onConfirm) {
                    Text("Confirm")
                }
            })
    }
}

@Composable
fun SingleChoiceDialog(
    show: Boolean,
    chessController: ChessController,
    title: String,
    onOptionSelected: (String) -> Unit
) {
    if (show) {
        val reviveSymbols = chessController.getReviveSymbols()
        AlertDialog(onDismissRequest = {}, title = { Text(title) }, text = {
            Column {
                reviveSymbols.forEachIndexed { index, option ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)
                            .clickable { onOptionSelected(reviveSymbols[index]) }) {
                        RadioButton(
                            selected = index == 0,
                            onClick = { onOptionSelected(reviveSymbols[index]) })
                        Text(text = option)
                    }
                }
            }
        }, confirmButton = {
            TextButton(onClick = { onOptionSelected(reviveSymbols[0]) }) {
                Text("OK")
            }
        })
    }

}