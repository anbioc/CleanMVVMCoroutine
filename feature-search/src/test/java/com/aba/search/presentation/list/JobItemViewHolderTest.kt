package com.aba.search.presentation.list

import android.widget.ImageView
import android.widget.TextView
import com.aba.core.SOME_JOB_DOMAIN_MODEL
import com.aba.core.TestUtil.Companion.anyNotNull
import com.aba.core.robolectric.ViewHolderTestBase
import com.aba.core.util.ImageLoader
import com.aba.feature_search.R
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import junit.framework.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.assertj.core.api.Assertions.assertThat
import org.mockito.MockitoAnnotations.initMocks

class JobItemViewHolderTest : ViewHolderTestBase<JobItemViewHolder>() {

    private val mockCallback: SearchListAdapter.Callback = mock()
    private val mockImageLoader: ImageLoader = mock()

    private val title by lazy {
        subject.itemView.findViewById<TextView>(R.id.jobItemTitle)
    }

    private val description by lazy {
        subject.itemView.findViewById<TextView>(R.id.jobItemDescription)
    }

    private val imageView by lazy {
        subject.itemView.findViewById<ImageView>(R.id.jobItemImageView)
    }

    @Before
    fun `setup`() {
        initMocks(this)
        createViewHolder(R.layout.item_job) {
            JobItemViewHolder(it, mockCallback, mockImageLoader)
        }
    }


    @Test
    fun `whenBindView thenJobTitleIsSet andThenJobDescriptionIsSet andThenImageIsLoaded andThenItemViewClickListenerIsSet`() {
        whenBindView()
        thenJobTitleIsSet()
        thenJobDescriptionIsSet()
        thenImageIsLoaded()
        thenItemViewClickListenerIsSet()
    }

    @Test
    fun `whenBindView whenOnItemViewClicked thenCallbackIsCalled`(){
        whenBindView()
        whenOnItemViewClicked()
        thenCallbackIsCalled()
    }

    /*
     * When
     */
    private fun whenBindView() {
        subject.bind(SOME_JOB_DOMAIN_MODEL)
    }

    private fun whenOnItemViewClicked() {
        subject.itemView.performClick()
    }

    /*
     * Then
     */
    private fun thenJobTitleIsSet() {
        with(title) {
            assertThat(text).isEqualTo(SOME_JOB_DOMAIN_MODEL.title)
        }
    }

    private fun thenJobDescriptionIsSet() {
        with(description) {
            assertThat(text).isEqualTo(SOME_JOB_DOMAIN_MODEL.description)
        }
    }

    private fun thenImageIsLoaded() {
        verify(mockImageLoader).loadImage(
            imageView,
            R.drawable.ic_launcher_background,
            R.drawable.ic_launcher_foreground,
            SOME_JOB_DOMAIN_MODEL.companyUrl
        )
    }

    private fun thenItemViewClickListenerIsSet() {
        assertTrue(subject.itemView.hasOnClickListeners())
    }

    private fun thenCallbackIsCalled() {
        verify(mockCallback).onJobItemClicked(anyNotNull())
    }
}