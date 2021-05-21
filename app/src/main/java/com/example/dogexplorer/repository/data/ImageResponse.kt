package com.example.dogexplorer.repository.data

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize


@Parcelize
data class ImageResponse(
    @Expose
    @SerializedName("message")
    var message: List<String>,
    @Expose
    @SerializedName("status")
    var status: String
) : Parcelable
