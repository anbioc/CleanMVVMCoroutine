package com.aba.core.base

sealed class ResultResponse {
    data class Success<T>(val data: T) : ResultResponse()
    data class Failure(val error: Throwable) : ResultResponse()
    class Loading : ResultResponse()


    fun isSuccess(): Boolean = this is Success<*>
    fun isFailure(): Boolean = this is Failure
    fun isLoading(): Boolean = this is Loading
    fun isEmpty(): Boolean {
        return if (isSuccess()){
            (this as Success<List<*>>).data.isEmpty()
        }else {
            true
        }
    }
}