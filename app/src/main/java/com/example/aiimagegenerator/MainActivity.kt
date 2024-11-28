package com.example.aiimagegenerator

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresExtension
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.aiimagegenerator.domain.Screen
import com.example.aiimagegenerator.presentation.screens.HomeScreen
import com.example.aiimagegenerator.presentation.MainViewModel
import com.example.aiimagegenerator.presentation.screens.ImageScreen
import com.example.aiimagegenerator.presentation.screens.LoadingScreen
import com.example.aiimagegenerator.ui.theme.AiImageGeneratorTheme
import org.koin.androidx.viewmodel.ext.android.viewModel // Import Koin ViewModel delegate

class MainActivity : ComponentActivity() {

    val viewModel : MainViewModel by viewModel()

    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AiImageGeneratorTheme {
                val navController = rememberNavController()
                Navigation(viewModel, navController)

            }
        }
    }
}


@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@Composable
fun Navigation(viewModel: MainViewModel, navController: NavHostController) {

    NavHost(navController = navController, startDestination = Screen.HomeScreen.route){
        composable(Screen.HomeScreen.route){
            HomeScreen(viewModel, navController)
        }
        composable(Screen.LoadingScreen.route){
            LoadingScreen(viewModel, navController)
        }
        composable(Screen.ImageScreen.route){
            ImageScreen(viewModel, navController)
        }
    }

}