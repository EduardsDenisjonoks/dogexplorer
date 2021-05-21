package com.example.dogexplorer.di

import com.example.dogexplorer.BuildConfig
import com.example.dogexplorer.repository.DogApi
import com.example.dogexplorer.repository.DogRepository
import com.example.dogexplorer.repository.DogRepositoryImpl
import com.example.dogexplorer.repository.data.BreedResponse
import com.example.dogexplorer.repository.data.getBreedResponseDeserializer
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideDOgApi(okHttpClient: OkHttpClient, gson: Gson) =
        createWebService<DogApi>(okHttpClient, gson, BuildConfig.BASE_URL)

    @Provides
    fun provideDogRepository(dogApi: DogApi): DogRepository = DogRepositoryImpl(dogApi)


    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder().apply {
            when (BuildConfig.DEBUG) {
                true -> this.addInterceptor(getHttpLoggingInterceptor())
            }
            this.addInterceptor { chain ->
                val requestBuilder = chain.request().newBuilder()
                requestBuilder.header("Content-Type", "application/json")
                requestBuilder.header("Accept", "application/json")
                return@addInterceptor chain.proceed(requestBuilder.build())
            }
        }.build()
    }

    @Provides
    fun provideGson(): Gson =
        GsonBuilder()
            .registerTypeAdapter(BreedResponse::class.java, getBreedResponseDeserializer())
            .excludeFieldsWithoutExposeAnnotation()
            .setLenient()
            .create()

    private fun getHttpLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor { message -> Timber.d(message) }.apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    private inline fun <reified T> createWebService(
        okHttpClient: OkHttpClient, gson: Gson, baseUrl: String
    ): T {
        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttpClient)
            .build()
        return retrofit.create(T::class.java)
    }
}