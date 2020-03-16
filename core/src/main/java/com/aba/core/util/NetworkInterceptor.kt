package com.aba.core.util

import com.aba.core.AppConstant
import com.aba.core.exception.NetworkException
import okhttp3.Interceptor
import okhttp3.Response
import java.net.InetAddress
import java.util.concurrent.TimeUnit


class NetworkInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {

        if (!checkConnectivity()) {
            throw NetworkException("network is not available!")
        }

        return chain.proceed(chain.withReadTimeout(AppConstant.TIME_OUT, TimeUnit.SECONDS).request())
    }

    private fun checkConnectivity(): Boolean {
        return try {
            val ipAddr = InetAddress.getByName("google.com")
            !ipAddr.equals("")

        } catch (e: Exception) {
            false
        }

    }

}
