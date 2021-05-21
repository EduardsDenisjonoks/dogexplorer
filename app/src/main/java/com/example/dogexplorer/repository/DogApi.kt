package com.example.dogexplorer.repository

import com.example.dogexplorer.repository.data.BreedResponse
import com.example.dogexplorer.repository.data.ImageResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface DogApi {

    @GET("breeds/list/all")
    suspend fun getAllBreeds(): Response<BreedResponse>

    @GET("breed/{breed}/images/random/{limit}")
    suspend fun getBreedRandomImages(
        @Path("breed") breed: String,
        @Path("limit") limit: Int = 10
    ): Response<ImageResponse>

}