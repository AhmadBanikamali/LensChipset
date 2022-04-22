package com.abcdandroid.lenschipset

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.abcdandroid.lenschipset.ui.theme.LensChipsetTheme
import com.google.accompanist.pager.ExperimentalPagerApi
import dev.chrisbanes.snapper.ExperimentalSnapperApi

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalSnapperApi::class, ExperimentalPagerApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LensChipsetTheme {
                LensChipset()
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    LensChipsetTheme {
        Greeting("Android")
    }
}