package com.aba.core.util

import com.aba.core.SOME_MESSAGE
import com.aba.core.robolectric.RobolectricTestBase
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test

class TextUtilTest: RobolectricTestBase(){

    companion object {
        const val START_TAG = "<p>"
        const val END_TAG = "</p>"
        const val HTML_TEXT = "$START_TAG$SOME_MESSAGE$END_TAG"
    }

    lateinit var result: String


    @Before
    fun `setup`(){
    }

    @Test
    fun `givenSomeHTMLText whenTrimHTML thenHTMLIsTrimmed`() {
        whenTrimHTML()
        thenHTMLIsTrimmed()
    }

    /*
     * When
     */
    private fun whenTrimHTML() {
        result = TextUtil.trimHTML(HTML_TEXT)
    }

    /*
     * Then
     */
    private fun thenHTMLIsTrimmed() {
        assertThat(result).doesNotContain("<p>")
        assertThat(result).isEqualTo(SOME_MESSAGE)
    }

}