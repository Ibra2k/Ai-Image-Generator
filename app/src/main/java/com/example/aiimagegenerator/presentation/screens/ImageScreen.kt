package com.example.aiimagegenerator.presentation.screens

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.aiimagegenerator.domain.Constants
import com.example.aiimagegenerator.domain.Screen
import com.example.aiimagegenerator.presentation.MainViewModel

@Composable
fun ImageScreen(viewModel: MainViewModel, navController: NavHostController) {

    val base64ImageString by viewModel.imageBase64.collectAsState()
    val errorMessage by viewModel.error.collectAsState()
    var bitmap: Bitmap? by remember { mutableStateOf(null) }

    //Create Bitmap from our base64ImageString
    LaunchedEffect(base64ImageString) {
        //Logging
        Log.d(Constants.TAG, "Inside Launched Effect")
        base64ImageString?.let { Log.d(Constants.TAG, "Image String: $it") }


        base64ImageString?.let { base64 ->
            try {
                val decodedBytes = Base64.decode(base64, Base64.DEFAULT)
                bitmap = BitmapFactory.decodeByteArray(decodedBytes, 0 , decodedBytes.size)
            } catch (e: Exception) {
                viewModel.setError("Failed to decode image: ${e.message}")
            }
        }
    }

    //Convert bitmap to ImageBitmap(accepted bitmap) -> Put ImageBitmap in Composable

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){

        bitmap?.asImageBitmap()
            ?.let {imageBitmap ->
                Image(
                    bitmap = imageBitmap,
                    contentDescription = "Ai Generated Image",
                    modifier = Modifier.fillMaxWidth()
                )
            }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ){
            Button(onClick = { navController.navigate(Screen.HomeScreen.route) }) {
                Text(text = "Back")
            }


            Button(onClick = { }){
                Text(text = "Save")
            }
        }

        // Show Error if Available
        errorMessage?.let { error ->
            Text(text = "Error: $error", fontSize = 16.sp, color = androidx.compose.ui.graphics.Color.Red)
        }

    }



}