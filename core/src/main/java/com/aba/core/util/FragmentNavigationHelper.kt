package com.aba.core.util

import com.aba.core.base.BaseFragment
import com.aba.core.domain.data.JobDomainModel

interface FragmentNavigationHelper {
    fun getSearchFragment() : BaseFragment
    fun getSearchFragmentTag() : String
    fun getDetailFragment(job: JobDomainModel): BaseFragment
    fun getDetailFragmentTag(): String
}