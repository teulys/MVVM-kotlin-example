package com.biblia.dogimages.model

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DogRepository {

    private fun  getRetrofit():Retrofit{
        return Retrofit.Builder()
            .baseUrl("https://dog.ceo/api/breed/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    suspend fun getDogImagesByBreeds(breed: String) : List<String>? {
        val call = getRetrofit().create(APIService::class.java).getDogsByBreeds("$breed/images")

        val dogs = call.body()
        return if (call.isSuccessful) {
            dogs?.image ?: emptyList()
        }  else {
            null;
        }
    }

}