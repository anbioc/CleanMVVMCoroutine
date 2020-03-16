package com.aba.app.navigation

import com.aba.core.base.BaseFragment
import com.aba.core.domain.data.JobDomainModel
import com.aba.core.util.FragmentNavigationHelper
import com.aba.detail.presentation.DetailFragment
import com.aba.search.presentation.SearchFragment
import javax.inject.Inject

class AppFragmentNavigationHelper @Inject constructor(): FragmentNavigationHelper {
    override fun getSearchFragment(): BaseFragment = SearchFragment.newInstance()
    override fun getSearchFragmentTag() = "SearchFragment"

    override fun getDetailFragment(job: JobDomainModel) = DetailFragment.newInstance(job)

    override fun getDetailFragmentTag() = "DetailFragment"
}