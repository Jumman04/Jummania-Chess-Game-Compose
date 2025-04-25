package com.jummania.utils

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay

class Toast {
    private var message by mutableStateOf<String?>(null)
    private var duration: Long = 3000L
    private var initialized = false

    fun show(message: String?, duration: Long = 3000L) {
        this.message = message
        this.duration = duration
        if (!initialized) {
            throw IllegalStateException("Toast must be initialized before showing a toast.")
        }
    }

    @Composable
    fun initialize() {

        LaunchedEffect(message) {
            delay(duration)
            message = null
        }

        if (!message.isNullOrBlank()) {
            Box(
                modifier = Modifier.fillMaxSize().padding(bottom = 40.dp),
                contentAlignment = Alignment.BottomCenter
            ) {
                Box(
                    modifier = Modifier.background(
                        color = Color.Black.copy(alpha = 0.8f), shape = RoundedCornerShape(12.dp)
                    ).padding(horizontal = 16.dp, vertical = 12.dp)
                ) {
                    Text(text = message!!, color = Color.White, fontSize = 16.sp)
                }
            }
        }

        initialized = true
    }
}
