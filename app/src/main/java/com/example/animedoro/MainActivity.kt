package com.example.animedoro

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
import androidx.navigation.NavHostController
import com.example.animedoro.ui.theme.AnimedoroTheme
import com.example.animedoro.ui.theme.primary

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AnimedoroTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = primary
                ) {
                    AnimeradoMainScreen()
                }
            }
        }
    }
}

@Composable
fun AnimeradoMainScreen() {
    WelcomeScreen()
}


@Preview(showBackground = true)
@Composable
fun AnimeradoPreview() {
    AnimedoroTheme {
        AnimeradoMainScreen()
    }
}
