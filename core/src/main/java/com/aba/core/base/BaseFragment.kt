package com.aba.core.base

import android.content.Context
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.aba.core.util.FragmentNavigationHelper
import dagger.android.support.AndroidSupportInjection
import dagger.android.support.DaggerFragment
import javax.inject.Inject

abstract class BaseFragment: DaggerFragment() {

    @Inject
    lateinit var fragmentNavigator: FragmentNavigationHelper




//    abstract fun getViewModelClass(): Class<VM>

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

}