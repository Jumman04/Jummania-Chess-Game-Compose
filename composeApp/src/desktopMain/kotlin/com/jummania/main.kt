package com.jummania

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application


fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "Jummania Chess Game Compose",
    ) {

        var toastMessage by remember { mutableStateOf("") }
        var showToast by remember { mutableStateOf(false) }

        // Auto-hide toast
        LaunchedEffect(showToast) {
            if (showToast) {
                kotlinx.coroutines.delay(3000)
                showToast = false
            }
        }

        App { message ->
            toastMessage = message
            showToast = true
        }

        Toast(message = toastMessage, visible = showToast)
    }
}