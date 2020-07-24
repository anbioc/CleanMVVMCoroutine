package com.aba.core.base

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf


interface BaseRepository<PARAM: QueryParam> {

    suspend fun getLocalResult(param: PARAM): ResultResponse
    suspend fun getRemoteResult(param: PARAM): ResultResponse

    suspend fun getResult(param: PARAM, strategy: QueryStrategy): Flow<ResultResponse> = flow {
        if (strategy.isRemote()) {
            emit(getRemoteResult(param))
        } else {
            emit(getLocalResult(param))
            emit(getRemoteResult(param))
        }

    }

}