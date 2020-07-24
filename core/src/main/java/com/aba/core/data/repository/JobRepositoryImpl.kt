package com.aba.core.data.repository

import com.aba.core.base.QueryStrategy
import com.aba.core.base.ResultResponse
import com.aba.core.data.datasource.local.JobLocalDataSource
import com.aba.core.data.datasource.remote.JobRemoteDataSource
import com.aba.core.data.mapper.JobMapper
import com.aba.core.data.model.JobItem
import com.aba.core.domain.usecase.JobUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class JobRepositoryImpl @Inject constructor(
    private val remoteDataSource: JobRemoteDataSource,
    private val localDataSource: JobLocalDataSource,
    private val jobMapper: JobMapper
) : JobRepository<JobUseCase.JobSearchParams>() {

//    override suspend fun getJobs(
//        param: JobUseCase.JobSearchParams,
//        strategy: QueryStrategy
//    ) = flow<ResultResponse> {
//        if (strategy.isRemote()) {
//            remoteDataSource.getJobs(param.description, param.location).run {
//                if (this.isSuccess()) {
//                    localDataSource.insertOrUpdate((this as ResultResponse.Success<List<JobItem>>).data)
//                    emit(jobMapper.map(this))
//                } else {
//                    emit(this)
//                }
//            }
//        } else {
//            localDataSource.get(param.description).run {
//                if (isEmpty().not()) {
//                    emit(jobMapper.map(this))
//                } else {
//                    emit(ResultResponse.Success<List<JobItem>>(emptyList()))
//                }
//            }
//        }
//    }


    override suspend fun getJobs(
        param: JobUseCase.JobSearchParams,
        strategy: QueryStrategy
    ): Flow<ResultResponse> = getResult(param, strategy)

    override suspend fun getLocalResult(param: JobUseCase.JobSearchParams) =
        localDataSource.get(param.description).run {
            if (isEmpty().not()) {
                jobMapper.map(this)
            } else {
                ResultResponse.Success<List<JobItem>>(emptyList())
            }
        }


    override suspend fun getRemoteResult(param: JobUseCase.JobSearchParams) =
        remoteDataSource.getJobs(param.description, param.location).run {
            if (this.isSuccess()) {
                localDataSource.insertOrUpdate((this as ResultResponse.Success<List<JobItem>>).data)
                jobMapper.map(this)
            } else {
                this
            }
        }

//    override suspend fun getJobs(
//        param: JobUseCase.JobSearchParams,
//        strategy: QueryStrategy
//    ): ResultResponse = with(localDataSource.get(param.description)) {
//        return if (isEmpty().not()) {
//            jobMapper.map(this)
//        } else {
//            remoteDataSource.getJobs(param.description, param.location).run {
//                return if (this.isSuccess()) {
//                    localDataSource.insertOrUpdate((this as ResultResponse.Success<List<JobItem>>).data)
//                    jobMapper.map(this)
//                } else {
//                    this
//                }
//            }
//        }
//
//    }

}