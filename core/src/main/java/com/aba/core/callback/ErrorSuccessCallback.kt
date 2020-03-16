package com.aba.core.callback

interface ErrorSuccessCallback {
    fun showLoadingSpinner()
    fun hideLoadingSpinner()
    fun displayGenericErrorMessage()
    fun displayNetworkErrorMessage()
    fun displayCustomError(title: String, msg: String)
}