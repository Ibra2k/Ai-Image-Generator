package com.example.aiimagegenerator.presentation

import android.content.ContentValues
import android.content.Context
import android.graphics.Bitmap
import android.os.Build
import android.provider.MediaStore
import android.util.Log
import com.example.aiimagegenerator.domain.Constants

import androidx.annotation.RequiresExtension
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aiimagegenerator.data.ApiService
import com.example.aiimagegenerator.data.ImageRepository
import com.example.aiimagegenerator.data.ImageRepositoryImpl
import com.example.aiimagegenerator.data.ImageRequest
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


class MainViewModel(private val repository: ImageRepository) : ViewModel() {

    private val _imageBase64 = MutableStateFlow<String?>(null)
    val imageBase64: StateFlow<String?> = _imageBase64

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error




    fun generateImage(prompt: String) {
        viewModelScope.launch {
            _imageBase64.value = Constants.LOADING
            val result = repository.generateImage(prompt)
            result
                .onSuccess { imageBase64 ->
                    _imageBase64.value = imageBase64
                    Log.d(Constants.TAG, "Base64Image Value found!")
                    _error.value = null
                }
                .onFailure { throwable ->
                    setError(throwable.message ?: "Unknown error occurred")
                }
        }
    }

    fun setError(message: String) {
        _error.value = message
    }



}