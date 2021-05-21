package com.example.dogexplorer.repository.data

import android.os.Parcelable
import com.google.gson.JsonDeserializer
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import timber.log.Timber

@Parcelize
data class BreedResponse(
    @Expose
    @SerializedName("message")
    var breeds: List<Breed>,
    @Expose
    @SerializedName("status")
    var status: String
) : Parcelable

fun getBreedResponseDeserializer() = JsonDeserializer { jsonElement, _, _ ->
    try {
        val jsonObject = jsonElement.asJsonObject
        val status = jsonObject.get("status")?.asString ?: ""
        val messageJsonObject = jsonObject.get("message").asJsonObject

        val breeds = ArrayList<Breed>()

        for (key in messageJsonObject.keySet()) {
            val breed : String = key
            val subBreedJsonArray = messageJsonObject[key].asJsonArray
            if (subBreedJsonArray.size() == 0 || subBreedJsonArray == null) {
                breeds.add(Breed(breed.capitalize(), null, emptyList()))
            } else {
                subBreedJsonArray.forEach {
                    breeds.add(Breed(breed.capitalize(), it.asString?.capitalize(), emptyList()))
                }
            }
        }
        BreedResponse(breeds, status)
    } catch (ex: Exception) {
        Timber.tag("BreedResponseDeserializer").e(ex, "Unable to deserialize breed")
        BreedResponse(emptyList(), "error")
    }
}

