package com.example.animedoro

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
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

enum class AnimedoroScreen() {
    Welcome,
    SessionType,
    AllPreviousSessions
}

@Composable
fun AnimeradoMainScreen(modifier: Modifier = Modifier) {
    val navController = rememberNavController()

    Scaffold() { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = AnimedoroScreen.Welcome.name,
            modifier = modifier.padding(innerPadding)
        ) {
            composable(route = AnimedoroScreen.Welcome.name) {
                WelcomeScreen(
                    onAddNewSession = {
                        navController.navigate(AnimedoroScreen.SessionType.name)
                    },
                    allPreviousSessions  = {
                        navController.navigate(AnimedoroScreen.AllPreviousSessions.name)
                    }
                )
            }
            composable(route = AnimedoroScreen.SessionType.name) {
                SessionType(
                    backButton = {
                        navController.popBackStack()
                    }
                )
            }
            composable(route = AnimedoroScreen.AllPreviousSessions.name) {
                AllSessions(
                    backButton = {
                        navController.popBackStack()
                    }
                )
            }
        }

    }
    //WelcomeScreen()
}


@Preview(showBackground = true)
@Composable
fun AnimeradoPreview() {
    AnimedoroTheme {
        AnimeradoMainScreen()
    }
}
