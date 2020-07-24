package com.aba.core.base

sealed class QueryStrategy {
    fun isRemote(): Boolean = this == Remote
    fun isOfflineFirst(): Boolean = this == OfflineFirst

    object Remote: QueryStrategy()
    object OfflineFirst: QueryStrategy()
}