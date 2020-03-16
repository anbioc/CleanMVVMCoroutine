package com.aba.core.domain.usecase

import com.aba.core.base.BaseUseCase
import com.aba.core.base.ResultResponse
import com.aba.core.data.repository.JobRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class JobUseCase @Inject constructor(
    private val repository: JobRepository
) : BaseUseCase.ResourceUseCase<JobUseCase.JobUseCaseParams> {

    override suspend fun executeAsync(params: JobUseCaseParams): ResultResponse =
        withContext(Dispatchers.IO) {
            repository.getJobs(params.description, params.location)
        }


    class JobUseCaseParams(val description: String, val location: String) :
        BaseUseCase.UseCaseParams()
}