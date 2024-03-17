package com.adit.kandang.model.repository

import com.adit.kandang.model.data.remote.ApiHelper

class KandangRepository (
    private val apiHelper: ApiHelper
){

    suspend fun getKandangActive() = apiHelper.getKandangActive()
    suspend fun getKandangRehat() = apiHelper.getKandangRehat()
}