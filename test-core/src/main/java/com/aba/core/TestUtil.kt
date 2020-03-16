package com.aba.core

import org.mockito.Mockito

class TestUtil {

    companion object {

        fun <T> anyNotNull(type : Class<T>): T {
            Mockito.any(type)
            return uninitialized()
        }

        fun <T> anyNotNull(): T {
            Mockito.any<T>()
            return uninitialized()
        }

        private fun <T> uninitialized(): T = null as T

    }

}