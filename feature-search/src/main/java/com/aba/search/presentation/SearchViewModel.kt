package com.aba.search.presentation

import androidx.lifecycle.viewModelScope
import com.aba.core.base.BaseViewModel
import com.aba.core.base.ResultResponse
import com.aba.core.domain.usecase.JobUseCase
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import javax.inject.Inject

class SearchViewModel @Inject constructor(
    private val useCase: JobUseCase
) : BaseViewModel() {

    fun getData(description: String, location: String) = viewModelScope.launch(Main) {
        loadData(ResultResponse.Loading())
        useCase.executeAsync(JobUseCase.JobUseCaseParams(description, location)).run {
            loadData(this)
        }
    }

}