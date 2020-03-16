package com.aba.search.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.aba.core.extension.navigate
import com.aba.core.view.SimpleActivity
import com.aba.feature_search.R
import dagger.android.AndroidInjector
import dagger.android.support.HasSupportFragmentInjector


class MainNavigationActivity : SimpleActivity(), HasSupportFragmentInjector {
    override val contentResourceId: Int
        get() = R.layout.activity_main


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        navigate(
            R.id.mainActivityContainer,
            fragmentNavigator.getSearchFragment(),
            fragmentNavigator.getSearchFragmentTag()
        )
    }

    override fun supportFragmentInjector(): AndroidInjector<Fragment> = fragmentInjector



}
