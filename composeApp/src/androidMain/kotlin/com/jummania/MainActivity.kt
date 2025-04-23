package com.jummania

import android.content.res.AssetManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            App(Font(path = "symbola.ttf", assetManager = assets))
        }
    }
}

//@Preview
@Composable
fun AppAndroidPreview() {
    // App(Font(R.font.symbola))
}