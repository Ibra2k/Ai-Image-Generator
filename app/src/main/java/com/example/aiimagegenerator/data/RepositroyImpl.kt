package com.example.aiimagegenerator.data

import android.net.http.HttpException
import android.os.Build
import androidx.annotation.RequiresExtension
import java.io.IOException

class ImageRepositoryImpl(private val apiService: ApiService) : ImageRepository {

    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    override suspend fun generateImage(prompt: String): Result<String> {
        return try {
            val response = apiService.generateImage(ImageRequest(prompt))
            if (response.isSuccessful) {
                // Safely extract the image data
                response.body()?.let {
                    Result.success(it.image)
                } ?: Result.failure(Exception("Response body is null"))
            } else {
                Result.failure(Exception("Error ${response.code()}: ${response.message()}"))
            }
        } catch (e: HttpException) {
            Result.failure(Exception("HTTP error: ${e.message}"))
        } catch (e: IOException) {
            Result.failure(Exception("Network error: ${e.message}"))
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
