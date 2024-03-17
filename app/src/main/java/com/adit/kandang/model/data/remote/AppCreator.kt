package com.adit.kandang.model.data.remote

import android.app.Application

class AppCreator : Application() {
    companion object {

        private var mApiHelper:ApiHelper? = null
        fun getApiHelperInstance():ApiHelper{
            if(mApiHelper==null){
                mApiHelper = ApiHelper(ApiConfig.getApiService())
            }
            return mApiHelper!!
        }

    }
}