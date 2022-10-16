package com.example.animedoro

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.animedoro.model.Tasks
import com.example.animedoro.ui.theme.AnimedoroTheme
import com.example.animedoro.ui.theme.primary

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        var music = playMusic(R.raw.song, this)
        super.onCreate(savedInstanceState)
        setContent {
            AnimedoroTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = primary
                ) {
                    AnimeradoMainScreen(
                        music = music
                    )
                }
            }
        }
    }
}

enum class AnimedoroScreen() {
    Welcome,
    SessionType,
    AllPreviousSessions,
    AddYourTasks,
    StartScreen,
    Session,
    BreakScreen
}

@Composable
fun AnimeradoMainScreen(
    modifier: Modifier = Modifier,
    music: playMusic
) {
    val navController = rememberNavController()
    val tasks = remember {
        mutableStateListOf<Tasks>()
    }

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
                    },
                    onDefault = {
                        navController.navigate(AnimedoroScreen.AddYourTasks.name)
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
            composable(route = AnimedoroScreen.AddYourTasks.name) {
                AddYourTasksScreen(
                    backButton = {
                        navController.popBackStack()
                    },
                    onNext = {
                        navController.navigate(AnimedoroScreen.StartScreen.name)
                    },
                    tasks = tasks
                )
            }
            composable(route = AnimedoroScreen.StartScreen.name) {
                StartYourSessionScreen(
                    backButton = {
                        navController.popBackStack()
                    },
                    onStart = {
                        navController.navigate(AnimedoroScreen.Session.name)
                    }
                )
            }
            composable(route = AnimedoroScreen.Session.name) {
                Session()
            }
            composable(route = AnimedoroScreen.BreakScreen.name) {
                BreakScreen(
                    backButton = {
                        navController.popBackStack()
                    },
                    homeButton = {
                        navController.navigate(AnimedoroScreen.Welcome.name)
                    }
                )
            }
        }

    }
    //WelcomeScreen()
}


//@Preview(showBackground = true)
//@Composable
//fun AnimeradoPreview() {
//    AnimedoroTheme {
//        AnimeradoMainScreen(music = music)
//    }
//}