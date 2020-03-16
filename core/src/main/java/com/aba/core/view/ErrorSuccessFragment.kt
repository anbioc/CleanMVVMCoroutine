package com.aba.core.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import com.aba.core.R
import com.aba.core.base.BaseFragment
import com.aba.core.base.BaseViewModel
import com.aba.core.base.ResultResponse
import com.aba.core.callback.ErrorSuccessCallback
import com.aba.core.extension.hide
import com.aba.core.extension.observeLiveData
import com.aba.core.extension.show
import kotlinx.android.synthetic.main.fragment_error_success.*
import kotlinx.android.synthetic.main.fragment_error_success.view.*

abstract class ErrorSuccessFragment : BaseFragment(),
    ErrorSuccessCallback {

    protected abstract val contentResourceId: Int

    internal companion object {
        internal const val VIEW_INDEX_CONTENT = 0
        internal const val VIEW_INDEX_ERROR = 1
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_error_success, container, false)
        view.errorContentContainer.addView(layoutInflater.inflate(contentResourceId, null))
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewListeners()
//        observeLiveData(viewModel.state) {
//            processResponseResult(it)
//        }
    }

    abstract fun processResponseResult(result: ResultResponse)
    abstract fun initViewListeners()

    open fun onErrorAction() {

    }

    open fun onEmptyAction() {

    }

    override fun showLoadingSpinner() {
        switchToView(VIEW_INDEX_CONTENT)
        errorLoadingView.show()
    }

    override fun hideLoadingSpinner() {
        errorLoadingView.hide()
    }

    override fun displayGenericErrorMessage() {
        showErrorScreen(
            getString(R.string.generic_fail_title),
            getString(R.string.generic_network_error),
            getString(R.string.generic_error_button_text)
        )
    }

    override fun displayNetworkErrorMessage() {
        showErrorScreen(
            getString(R.string.generic_fail_title),
            getString(R.string.generic_network_error),
            getString(R.string.generic_error_button_text)
        )
    }

    override fun displayCustomError(title: String, msg: String) {
        showErrorScreen(
            title,
            msg,
            getString(R.string.generic_error_button_text)
        )
    }


    private fun showErrorScreen(title: String, message: String, buttonTitle: String) {
        switchToView(VIEW_INDEX_ERROR)
    }

    private fun switchToView(viewIndexContent: Int) {
        with(errorSuccessViewSwitcher) {
            if (viewIndexContent != displayedChild) {
                displayedChild = viewIndexContent
            }
        }
    }
}