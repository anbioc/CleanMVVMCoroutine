package com.aba.core.base

import com.aba.core.SOME_JOB_RESPONSE_ITEMS
import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class ResultResponseTest {

    lateinit var subject: ResultResponse
    var failureResult: Boolean = false
    var successResult: Boolean = false
    var loadingResult: Boolean = false


    @Before
    fun `setup`() {
        failureResult = false
        successResult = false
        loadingResult = false
    }

    @Test
    fun `givenFailureSubject whenIsFailure thenSubjectIsFailure`() {
        givenFailureSubject()
        whenIsFailure()
        thenSubjectIsFailure(true)
        thenSubjectIsSuccess(false)
    }

    @Test
    fun `givenSuccessSubject whenIsSubject thenSubjectIsSuccess`() {
        givenSuccessSubject()
        whenIsSubject()
        thenSubjectIsFailure(false)
        thenSubjectIsSuccess(true)
    }

    @Test
    fun `givenLoadingSubject whenIsLoading thenSubjectIsLoading`() {
        givenLoadingSubject()
        whenIsLoading()
        thenSubjectIsFailure(false)
        thenSubjectIsSuccess(false)
        thenSubjectIsLoading(true)
    }

    /*
     * Given
     */
    private fun givenFailureSubject() {
        subject = ResultResponse.Failure(Throwable())
    }

    private fun givenSuccessSubject() {
        subject = ResultResponse.Success(SOME_JOB_RESPONSE_ITEMS)
    }

    private fun givenLoadingSubject() {
        subject = ResultResponse.Loading()
    }

    /*
     * When
     */
    private fun whenIsFailure() {
        failureResult = subject.isFailure()
    }

    private fun whenIsSubject() {
        successResult = subject.isSuccess()
    }

    private fun whenIsLoading() {
        loadingResult = subject.isLoading()
    }

    /*
     * Then
     */
    private fun thenSubjectIsFailure(isFailure: Boolean) {
        if (isFailure) {
            assertThat(failureResult).isTrue()
        } else {
            assertThat(failureResult).isFalse()
        }
    }

    private fun thenSubjectIsSuccess(isSuccess: Boolean) = if (isSuccess) {
        assertThat(successResult).isTrue()
    } else {
        assertThat(successResult).isFalse()
    }

    private fun thenSubjectIsLoading(loading: Boolean) = if (loading) {
        assertThat(loadingResult).isTrue()
    } else {
        assertThat(loadingResult).isFalse()
    }
}