package com.aba.search.presentation

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.aba.core.base.ResultResponse
import com.aba.core.domain.data.JobDomainModel
import com.aba.core.extension.*
import com.aba.core.util.ImageLoader
import com.aba.core.view.ErrorSuccessFragment
import com.aba.feature_search.R
import com.aba.search.presentation.list.SearchListAdapter
import kotlinx.android.synthetic.main.inc_search_header.*
import kotlinx.android.synthetic.main.inc_search_list.*
import kotlinx.android.synthetic.main.inc_search_submit.*
import javax.inject.Inject


class SearchFragment : ErrorSuccessFragment(), SearchListAdapter.Callback {

    override val contentResourceId = R.layout.fragment_search
    val viewModel: SearchViewModel by lazy {
        viewModelFactory.create(SearchViewModel::class.java)
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var imageLoader: ImageLoader

    @Inject
    lateinit var adapter: SearchListAdapter

    companion object {
        fun newInstance() = SearchFragment().apply {
            arguments = Bundle()
        }
    }

    /*
     * Callback
     */
    override fun onJobItemClicked(jobItem: JobDomainModel) {
        // TODO: navigate to detail screen
    }

    /*
     * Public
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        observeLiveData(viewModel.state) {
            processResponseResult(it)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        jobList.setupLinearList(adapter, true)
    }

    override fun initViewListeners() {
        submitButton.setOnClickListener {
            getData()
        }
        jobList.setOnScrollDirectionListener({
            submitButton.hide()
        }, {
            submitButton.show()
        })
    }

    override fun processResponseResult(result: ResultResponse) {
        when {
            result.isSuccess() -> {
                hideLoadingSpinner()
                loadListData((result as ResultResponse.Success<List<JobDomainModel>>).data)
            }
            result.isFailure() -> {
                hideLoadingSpinner()
                displayCustomError(
                    getString(R.string.generic_fail_title),
                    (result as ResultResponse.Failure).error.message ?: ""
                )
            }
            result.isLoading() -> {
                showLoadingSpinner()
            }
        }
    }

    /*
     * Private
     */
    private fun loadListData(data: List<JobDomainModel>) {
        adapter.itemList = data
    }

    private fun getData() {
        viewModel.getData(searchField.getFieldTextValue(), getString(R.string.location))
    }

}