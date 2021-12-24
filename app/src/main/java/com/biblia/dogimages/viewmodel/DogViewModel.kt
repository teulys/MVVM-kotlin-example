package com.biblia.dogimages.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.biblia.dogimages.model.DogRepository

class DogViewModel(application: Application) : AndroidViewModel(application) {

    private var imageDogList: MutableLiveData<List<String>> = MutableLiveData<List<String>>()
    private var errorMessege: MutableLiveData<String> = MutableLiveData<String>()

    suspend fun getDogsByBreends(breend: String) : MutableLiveData<List<String>>? {
        val dogRepository = DogRepository()

        val dogList: List<String>? = dogRepository.getDogImagesByBreeds(breend)

        if (dogList.isNullOrEmpty()){
            errorMessege.postValue("No se encontro esta raza")
            return null;
        }

        imageDogList.postValue(dogList)

        return imageDogList!!
    }

    fun getErrorMessage() : MutableLiveData<String> {
        return errorMessege
    }
}