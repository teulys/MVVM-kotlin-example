package com.biblia.dogimages.model.entities

import com.google.gson.annotations.SerializedName

data class DogModel(@SerializedName("status") val status: String, @SerializedName("message") val image: List<String>)