package com.adit.kandang.model.data.remote

import retrofit2.http.GET

interface ApiService {
    @GET("kandang_aktif")
    suspend fun getKandangActive(): List<ResponseKandang>

    @GET("kandang_rehat")
    suspend fun getKandangRehat(): List<ResponseKandang>
}