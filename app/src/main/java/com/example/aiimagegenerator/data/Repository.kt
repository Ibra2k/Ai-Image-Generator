package com.example.aiimagegenerator.data

interface ImageRepository {

    suspend fun generateImage(prompt: String): Result<String>
}

