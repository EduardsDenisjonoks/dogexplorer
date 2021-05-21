package com.example.dogexplorer

import androidx.lifecycle.*
import com.example.dogexplorer.core.network.ApiResponse
import com.example.dogexplorer.custom.SingleLiveEvent
import com.example.dogexplorer.repository.DogRepository
import com.example.dogexplorer.repository.data.Breed
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class DogBreedsViewModel @Inject constructor(
    private val dogRepository: DogRepository
) : ViewModel() {

    private val errorLiveData = SingleLiveEvent<String>()
    private val breedsLiveData = MediatorLiveData<List<Breed>>()

    val isLoadingLiveData = MutableLiveData(false)

    init {
        fetchBreeds()
    }

    fun getBreedsLiveData(): LiveData<List<Breed>> = breedsLiveData
    fun getErrorLiveData(): LiveData<String> = errorLiveData

    private fun setLoadingState(isLoading: Boolean) {
        isLoadingLiveData.postValue(isLoading)
    }

    private fun setBreeds(breeds: List<Breed>) {
        breedsLiveData.postValue(breeds)
    }

    private fun setError(errorMessage: String) {
        errorLiveData.postValue(errorMessage)
    }

    private fun fetchBreeds() {
        viewModelScope.launch(Dispatchers.IO) {
            setLoadingState(true)
            when (val result = dogRepository.getAllBreeds()) {
                is ApiResponse.Success -> {
                    Timber.i("Breeds fetched")
                    setBreeds(result.data)
                }
                is ApiResponse.Error -> {
                    Timber.e(result.appError, "Unable to fetch breeds")
                    setError(result.appError.localizedMessage ?: "Unable to fetch breeds")
                }
            }
            setLoadingState(false)
        }
    }
}