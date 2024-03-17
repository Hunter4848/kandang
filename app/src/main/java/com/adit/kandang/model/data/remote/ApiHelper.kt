package com.adit.kandang.model.data.remote

class ApiHelper (private val apiInterface: ApiService) {
    suspend fun getKandangActive() = apiInterface.getKandangActive()
    suspend fun getKandangRehat() = apiInterface.getKandangRehat()
}