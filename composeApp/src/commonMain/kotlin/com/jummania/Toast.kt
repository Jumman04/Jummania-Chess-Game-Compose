package com.jummania

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Toast(message: String, visible: Boolean) {
    if (visible) {
        Box(
            modifier = Modifier.fillMaxSize().padding(bottom = 40.dp)
        ) {
            Box(
                modifier = Modifier.align(Alignment.BottomCenter).background(
                        color = Color.Black.copy(alpha = 0.8f),
                        shape = RoundedCornerShape(12.dp)
                    ).padding(horizontal = 16.dp, vertical = 12.dp)
            ) {
                Text(
                    text = message, color = Color.White, fontSize = 16.sp
                )
            }
        }
    }
}