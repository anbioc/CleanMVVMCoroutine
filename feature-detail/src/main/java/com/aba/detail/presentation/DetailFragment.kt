package com.aba.detail.presentation

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.aba.core.base.ResultResponse
import com.aba.core.domain.data.JobDomainModel
import com.aba.core.view.ErrorSuccessFragment
import com.aba.feature_detail.R
import javax.inject.Inject

class DetailFragment: ErrorSuccessFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    companion object {
        const val JOB_ITEM_KEY = "JOB_ITEM_KEY"
        fun newInstance(job: JobDomainModel) = DetailFragment().apply {
            arguments = Bundle().apply {
                putParcelable(JOB_ITEM_KEY, job)
            }
        }
    }

    override val contentResourceId: Int
        get() = R.layout.detail_fragment


    override fun processResponseResult(result: ResultResponse) {

    }

    override fun initViewListeners() {

    }

}