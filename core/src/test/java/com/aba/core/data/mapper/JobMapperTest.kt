package com.aba.core.data.mapper

import com.aba.core.*
import com.aba.core.base.ResultResponse
import com.aba.core.domain.data.JobDomainModel
import com.google.common.truth.Truth.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class JobMapperTest {

    @InjectMocks
    lateinit var subject: JobMapper

    private val input = ResultResponse.Success(SOME_JOB_RESPONSE_ITEMS)
    private lateinit var result: ResultResponse



    @Test
    fun `givenValidResult whenOnMap thenResultIsMapped`(){
        whenOnMap()
        thenResultIsMapped()
    }

    /*
     * When
     */
    private fun whenOnMap() {
        result = subject.map(input)
    }

    /*
     * Then
     */
    private fun thenResultIsMapped() {
        with((result as ResultResponse.Success<List<JobDomainModel>>).data){
            assertThat(size).isEqualTo(1)
            with(this[0]){
                assertThat(id).isEqualTo(SOME_ID)
                assertThat(company).isEqualTo(SOME_COMPANY)
                assertThat(companyLogo).isEqualTo(SOME_COMPANY_LOGO)
                assertThat(companyUrl).isEqualTo(SOME_COMPANY_URL)
                assertThat(description).isEqualTo(SOME_DESCRIPTION)
                assertThat(howToApply).isEqualTo(SOME_HOW_TO_APPLY)
                assertThat(location).isEqualTo(SOME_LOCATION)
                assertThat(title).isEqualTo(SOME_TITLE)
                assertThat(url).isEqualTo(SOME_URL)
            }
        }
    }

}