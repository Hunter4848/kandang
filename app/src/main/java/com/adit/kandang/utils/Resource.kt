package com.adit.kandang.utils

import com.adit.kandang.model.StatusResponse

data class Resource<out T>
    (val status: StatusResponse, val data:T?, val message:String?){

    companion object{

        fun <T> success(data:T): Resource<T> =
            Resource(status = StatusResponse.SUCCESS, data = data, message = null)

        fun <T> error(data:T?, message: String?):Resource<T> =
            Resource(status = StatusResponse.FAILURE, data = data, message = message)

        fun <T> loading(data:T?):Resource<T> =
            Resource(status = StatusResponse.LOADING, data = data, message = null)
    }

}