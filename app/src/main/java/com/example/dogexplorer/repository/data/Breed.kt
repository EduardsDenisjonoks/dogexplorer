package com.example.dogexplorer.repository.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Breed(
    var breedName: String,
    var subBreedName: String?,
    var images: List<String>
) : Parcelable
