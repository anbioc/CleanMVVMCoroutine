package com.aba.search.presentation

import androidx.lifecycle.viewModelScope
import com.aba.core.base.BaseViewModel
import com.aba.core.base.QueryStrategy
import com.aba.core.base.ResultResponse
import com.aba.core.domain.usecase.JobUseCase
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class SearchViewModel @Inject constructor(
    private val useCase: JobUseCase
) : BaseViewModel() {

//    fun getData(description: String, location: String) = viewModelScope.launch(Main) {
//        loadData(ResultResponse.Loading())
//        useCase.executeAsync(JobUseCase.JobSearchParams(description, location)).run {
//            loadData(this)
//        }
//    }

    fun getData(description: String, location: String) = viewModelScope.launch(Main){
        loadData(ResultResponse.Loading())
        useCase.executeAsync(JobUseCase.JobSearchParams(description, location),
            QueryStrategy.OfflineFirst).collect {
            loadData(it)
        }
    }

}