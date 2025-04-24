package com.jummania

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            App {
                Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
            }
        }
    }
}

//@Preview
@Composable
fun AppAndroidPreview() {
    // App(Font(R.font.symbola))
}