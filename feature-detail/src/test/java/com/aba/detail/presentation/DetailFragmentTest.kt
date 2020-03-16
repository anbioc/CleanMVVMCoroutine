package com.aba.detail.presentation

import android.os.Bundle
import com.aba.core.SOME_JOB_DOMAIN_MODEL
import com.aba.core.robolectric.FragmentTestBase
import org.junit.Before
import org.junit.Test
import org.assertj.android.api.Assertions.assertThat

class DetailFragmentTest: FragmentTestBase<DetailFragment> (){

    @Before
    fun `setup`(){
        createFragmentWithArguments()
    }

    @Test
    fun `whenOnCreate thenViewIsCreated`(){
        thenViewIsCreated()
    }

    /*
     * Then
     */
    private fun thenViewIsCreated() {
        assertThat(fragment.view).isNotNull
        assertThat(fragment.view).isVisible
    }

    /*
     * Helper
     */
    private fun createFragmentWithArguments() {
        createFragmentWith {
            apply {
                it.arguments = Bundle().apply {
                    putParcelable(DetailFragment.JOB_ITEM_KEY, SOME_JOB_DOMAIN_MODEL)
                }
            }
        }
    }

}