package com.adit.kandang.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import com.adit.kandang.model.data.remote.ApiHelper
import com.adit.kandang.model.repository.KandangRepository
import com.adit.kandang.utils.Resource

class MainViewModel(private val mKandangRepo: KandangRepository) : ViewModel() {

    fun getKandangActive() = liveData {
        emit(Resource.loading(null))
        try {
            emit(Resource.success(mKandangRepo.getKandangActive()))
        } catch (e: Exception) {
            emit(Resource.error(null, e.message.toString()))
        }
    }

    fun getKandangRehat() = liveData {
        emit(Resource.loading(null))
        try {
            emit(Resource.success(mKandangRepo.getKandangRehat()))
        } catch (e: Exception) {
            emit(Resource.error(null, e.message.toString()))
        }
    }
}

@Suppress("UNCHECKED_CAST")
class ViewModelFactory(private val apiHelper: ApiHelper) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(KandangRepository(apiHelper)) as T
        }
        throw IllegalArgumentException("Class not found")
    }
}