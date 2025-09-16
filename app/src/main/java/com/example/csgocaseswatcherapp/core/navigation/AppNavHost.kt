package com.example.csgocaseswatcherapp.core.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kotlinx.serialization.Serializable

@Composable
fun AppNavHost(){
    val navController = rememberNavController()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .windowInsetsPadding(WindowInsets.systemBars)

    ){
        NavHost(
            navController = navController,
            startDestination = Screen.Start
        ){
            composable<Screen.Start> {

            }
        }
    }
}

sealed class Screen {
    @Serializable
    data object Start : Screen()
}
