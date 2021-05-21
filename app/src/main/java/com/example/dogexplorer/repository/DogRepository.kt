package com.example.dogexplorer.repository

import com.example.dogexplorer.core.network.ApiResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface DogRepository {

    suspend fun getAllBreeds(): ApiResponse<String>

    suspend fun getBreedRandomImages(breed: String, subBreed: String?) : ApiResponse<List<String>>

}

class DogRepositoryImpl @Inject constructor(
    private val dogApi: DogApi
) : DogRepository {

    override suspend fun getAllBreeds(): ApiResponse<String> {
        return ApiResponse.success("")
    }

    override suspend fun getBreedRandomImages(breed: String, subBreed: String?): ApiResponse<List<String>> {
        return withContext(Dispatchers.IO) {
            try {
                val breedPath = "$breed/" + if (subBreed.isNullOrBlank()) "" else "$subBreed/"
                val response = dogApi.getBreedRandomImages(breedPath)
                if (response.isSuccessful) {
                    val result = response.body()?.message
                    if (result == null) {
                        ApiResponse.empty()
                    } else {
                        ApiResponse.success(result)
                    }
                } else {
                    ApiResponse.error(Throwable("Something went wrong"))
                }
            } catch (ex: Exception) {
                ApiResponse.error(Throwable(ex.localizedMessage))
            }
        }
    }
}