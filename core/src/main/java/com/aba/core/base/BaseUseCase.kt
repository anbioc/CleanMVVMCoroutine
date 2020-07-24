package com.aba.core.base

import kotlinx.coroutines.flow.Flow

interface BaseUseCase {

    interface ResourceUseCase<params: QueryParam>: BaseUseCase{
        suspend fun executeAsync(params: params, strategy: QueryStrategy): Flow<ResultResponse>
    }

    abstract class UseCaseParams
}