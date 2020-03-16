package com.aba.splash

import android.os.Bundle
import android.os.Handler
import com.aba.core.base.BaseActivity
import com.aba.core.util.Activities
import com.aba.core.util.intentTo

class SplashActivity : BaseActivity() {

    private val handler by lazy {
        InnerHandler()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_simple)

        startSearchActivity()
    }

    private fun startSearchActivity() {
        handler.postDelayed({
            startActivity(intentTo(this, Activities.Main.MainActivity))
            finish()
        }, 1000)
    }


    /*
     * Helper
     */

    /**
     *    create an inner class to prevent memory leak.
     *    in java code we may use static classes for this purpose.
     */
    class InnerHandler : Handler()

}
