package com.aba.core.data.datasource.remote

import com.aba.core.*
import com.aba.core.base.ResultResponse
import com.aba.core.data.model.JobItem
import com.aba.core.data.net.JobsService
import com.google.common.truth.Truth.assertThat
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.given
import kotlinx.coroutines.runBlocking
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.ResponseBody
import okio.BufferedSource
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Response


@RunWith(MockitoJUnitRunner::class)
class JobRemoteDataSourceImplTestXX {

    @Mock
    lateinit var mockService: JobsService
    @Mock
    lateinit var rawResponse: okhttp3.Response

    lateinit var retrofitResponse: Response<List<JobItem>>

    @InjectMocks
    lateinit var subject: JobRemoteDataSourceImpl

    private lateinit var result: ResultResponse

    @Before
    fun `setup`(){

    }

    @Test
    fun `givenResponseIsSuccessful whenGetJobs thenResultIsSuccess`() = runBlocking {
        givenResponseIs(successful = true)
        whenGetJobs()
        thenResultIsSuccess()
    }

    @Test
    fun `givenResponseIsFailure whenGetJobs thenResultIsFailure`() = runBlocking{
        givenResponseIs(successful = false)
        whenGetJobs()
        thenResultIsFailure()
    }


    /*
     * Given
     */
    private suspend fun givenResponseIs(successful : Boolean) {
        given(rawResponse.message).willReturn(SOME_MESSAGE)
        if (successful){
            makeSuccessfulResponse()
        }else {
            makeFailureResponse()
        }
        given(mockService.getJobs(any(), any())).willReturn(retrofitResponse)
    }

    /*
     When
     */
    private suspend fun whenGetJobs() {
        result = subject.getJobs(SOME_DESCRIPTION, SOME_LOCATION)
    }

    /*
     Then
     */
    private suspend fun thenResultIsSuccess() {
        assertThat((result as ResultResponse.Success<List<JobItem>>).data.size).isEqualTo(1)
        with((result as ResultResponse.Success<List<JobItem>>).data[0]){
            assertThat(this.title).isEqualTo(SOME_TITLE)
            assertThat(this.description).isEqualTo(SOME_DESCRIPTION)
            assertThat(this.company).isEqualTo(SOME_COMPANY)
            assertThat(this.location).isEqualTo(SOME_LOCATION)
            assertThat(this.url).isEqualTo(SOME_URL)
        }

    }

    private suspend fun thenResultIsFailure() {
        assertThat((result as ResultResponse.Failure).error.message).isEqualTo("SOME_MESSAGE")
    }

    /*
     Helper
     */
    private suspend fun makeSuccessfulResponse() {
        retrofitResponse = Response.success(SOME_JOB_RESPONSE_ITEMS)
    }

    private suspend fun makeFailureResponse() {
        retrofitResponse = Response.error( CustomResponseBody(), rawResponse)
    }

    class CustomResponseBody: ResponseBody(){
        override fun contentLength(): Long = 100

        override fun contentType(): MediaType?  = "text/html".toMediaType()

        override fun source(): BufferedSource {
            TODO("Not yet implemented")
        }

        override fun toString(): String {
            return SOME_MESSAGE
        }
    }

}