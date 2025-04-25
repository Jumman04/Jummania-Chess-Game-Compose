package com.jummania.utils

interface UserNotifier {
    fun message(message: String)
    fun dialog(
        title: String, description: String, onConfirm: () -> Unit
    )

    fun arrayDialog(
        title: String, options: Array<String>, onSelected: (Int) -> Unit
    )
}
