package com.aba.core.view

import android.os.Bundle
import com.aba.core.R
import com.aba.core.base.BaseActivity
import com.aba.core.extension.makeFullScreen
import dagger.android.support.HasSupportFragmentInjector

abstract class SimpleActivity: BaseActivity() {

    protected abstract val contentResourceId: Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(contentResourceId)
    }

    /**
     * Handling fragment pop up navigation as when the first fragment is presented
     * then the host activity will be finished after hitting the back press. so that
     * the activity content is not presented.
     * this is a work-around as i could not use the Navigational component
     * in my modular approach.
     */
    override fun onBackPressed() {
        with(supportFragmentManager.fragments){
            if (size > 1){
                super.onBackPressed()
            }else {
                finish()
            }
        }

    }
}