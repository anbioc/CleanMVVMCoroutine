package com.aba.core.extension

import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import com.aba.core.base.BaseFragment

fun AppCompatActivity.makeFullScreen(){
    requestWindowFeature(Window.FEATURE_NO_TITLE)
    this.window.setFlags(
        WindowManager.LayoutParams.FLAG_FULLSCREEN,
        WindowManager.LayoutParams.FLAG_FULLSCREEN
    )
    supportActionBar?.apply {
        hide()
    }
}



fun AppCompatActivity.navigate(layout: Int, fragment: BaseFragment, tag: String) =
    supportFragmentManager.beginTransaction()
        .add(layout, fragment, tag)
        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
        .addToBackStack(null)
        .commit()