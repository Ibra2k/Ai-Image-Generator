package com.example.aiimagegenerator.presentation.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.aiimagegenerator.domain.Constants
import com.example.aiimagegenerator.domain.Screen
import com.example.aiimagegenerator.presentation.MainViewModel

@Composable
fun LoadingScreen(viewModel: MainViewModel, navController: NavHostController) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        CircularProgressIndicator()
        val imageString by viewModel.imageBase64.collectAsState()
        if(imageString != Constants.LOADING){
            navController.navigate(Screen.ImageScreen.route)
        }
    }
}