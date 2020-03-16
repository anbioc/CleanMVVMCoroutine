package com.aba.core.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


abstract class BaseViewModel  : ViewModel(){

    protected val _state = MutableLiveData<ResultResponse>()
    val state : LiveData<ResultResponse> = _state


    fun loadData(response: ResultResponse) {
        _state.postValue(response)
    }
}