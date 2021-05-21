package com.example.dogexplorer.repository

import com.example.dogexplorer.core.network.ApiResponse
import com.example.dogexplorer.repository.data.Breed
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.*
import javax.inject.Inject

interface DogRepository {

    suspend fun getAllBreeds(): ApiResponse<List<Breed>>

    suspend fun getBreedRandomImages(breed: String, subBreed: String?): ApiResponse<List<String>>

}

class DogRepositoryImpl @Inject constructor(
    private val dogApi: DogApi
) : DogRepository {

    override suspend fun getAllBreeds(): ApiResponse<List<Breed>> {
        return withContext(Dispatchers.IO) {
            try {
                val response = dogApi.getAllBreeds()
                if (response.isSuccessful) {
                    val result = response.body()
                    if (result == null) {
                        ApiResponse.success(emptyList())
                    } else {
                        result.breeds.forEach {
                            it.images = when (val imageResponse =
                                getBreedRandomImages(it.breedName, it.subBreedName)) {
                                is ApiResponse.Success -> {
                                    imageResponse.data
                                }
                                else -> emptyList()
                            }
                        }
                        ApiResponse.success(result.breeds)
                    }
                } else {
                    ApiResponse.error(Throwable("Something went wrong"))
                }
            } catch (ex: Exception) {
                ApiResponse.error(Throwable(ex.localizedMessage))
            }
        }
    }

    override suspend fun getBreedRandomImages(
        breed: String,
        subBreed: String?
    ): ApiResponse<List<String>> {
        return withContext(Dispatchers.IO) {
            try {
                val subBreedPath =
                    if (subBreed.isNullOrBlank()) "" else "/${subBreed.toLowerCase(Locale.getDefault())}"
                val breedPath = breed.toLowerCase(Locale.getDefault()) + subBreedPath

                val response = dogApi.getBreedRandomImages(breedPath)
                if (response.isSuccessful) {
                    val result = response.body()?.message
                    if (result == null) {
                        ApiResponse.success(emptyList())
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