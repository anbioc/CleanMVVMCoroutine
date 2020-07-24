package com.aba.app

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking
import org.junit.Test

class FlowMergePlayground {
    val flow1 = flowOf("A", "B", "C", "D")
    val flow2 = flowOf("ggg", "hhh", "fff", "sss")

    val processFlow1 = flow1.map {
        if (it == "C")
            throw Exception()

        if (it == "D")
            delay(200)
        it
        }.handleSuccessFailure()

    val processFlow2 = flow2.map {
        delay(500)
        it
    }.handleSuccessFailure()

//    private fun Flow<String>.handleSuccessFailure(): Flow<String> = flow {
//        try {
//            collect { value -> emit(value) }
//        } catch (e: Throwable) {
//            emit("error")
//        }
//    }.catch {
//
//    }

    private fun  Flow<String>.handleSuccessFailure() = this.map {
        it
    }.catch { e ->
        // TODO: Implement Timber for debugging purposes.
//        e.printStackTrace()
        emit("error")

    }

    @Test
    fun `execute`() = runBlocking {
        try{

        }catch (e: Exception){

        }

        getOfflineFirst()
            .collect {
                println(it)
            }

    }


    @ExperimentalCoroutinesApi
    private suspend fun getOfflineFirst() = flow {
        processFlow1
            .onCompletion {
                processFlow2.collect {
                    emit(it)
                }
            }.collect { emit(it) }
    }
}