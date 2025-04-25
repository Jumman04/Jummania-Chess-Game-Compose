package com.jummania.utils

interface UserNotifier {
    fun message(message: String)
    fun gameEndDialogue()
    fun revivePawnDialog(position: Int)
    fun afterRevival()
}
