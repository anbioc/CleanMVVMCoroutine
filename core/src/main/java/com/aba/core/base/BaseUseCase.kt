package com.aba.core.base

interface BaseUseCase {

    interface ResourceUseCase<params: UseCaseParams>: BaseUseCase{
        suspend fun executeAsync(params: params): ResultResponse
    }

    abstract class UseCaseParams
}