package com.aba.core.data.datasource.dao

import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.runner.AndroidJUnit4
import com.aba.core.*
import com.aba.core.data.datasource.AppDatabase
import com.aba.core.data.model.JobItem
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class JobDaoTest {

    private lateinit var appDatabase: AppDatabase
    private lateinit var subject: JobDao

    private lateinit var result: List<JobItem>

    @Before
    fun `setup`(){
        appDatabase = AppDatabase.getTestInstance(InstrumentationRegistry.getInstrumentation().targetContext)
        subject = appDatabase.jobDao()
    }

    @Test
    fun `whenOnInsert andWhenOnGetAll thenDataIsSaved`() = runBlocking {
        whenInsert()
        whenGetAll()
        thenDataIsSaved()
    }

    @Test
    fun `whenOnInsert andWhenOnGet thenDataIsSaved`() = runBlocking{
        whenInsert()
        whenOnGet()
        thenDataIsSaved()
    }

    @Test
    fun `whenOnReplace andWhenOnGet thenDataIsSaved`() = runBlocking{
        whenInsert()
        whenOnReplace()
        whenOnOtherGet()
        thenOtherDataIsSaved()
    }

    /*
     * When
     */
    private suspend fun whenInsert() {
        subject.insertOrUpdate(SOME_JOB_RESPONSE_ITEMS)
    }

    private suspend fun whenGetAll() {
        result = subject.getAll()
    }

    private suspend fun whenOnGet() {
        result = subject.get(SOME_DESCRIPTION)
    }

    private suspend fun whenOnOtherGet() {
        result = subject.get(SOME_OTHER_DESCRIPTION)
    }
    private suspend fun whenOnReplace() {
        subject.insertOrUpdate(SOME_OTHER_JOB_RESPONSE_ITEMS_SAME_ID)
    }

    /*
     *Then
     */
    private suspend fun thenDataIsSaved() {
        with(result[0]){
            assertThat(title).isEqualTo(SOME_TITLE)
            assertThat(company).isEqualTo(SOME_COMPANY)
            assertThat(companyLogo).isEqualTo(SOME_COMPANY_LOGO)
        }
    }

    private suspend fun thenOtherDataIsSaved() {
        with(result[0]){
            assertThat(id).isEqualTo(SOME_ID)
            assertThat(title).isEqualTo(SOME_OTHER_TITLE)
            assertThat(company).isEqualTo(SOME_OTHER_COMPANY)
            assertThat(companyLogo).isEqualTo(SOME_OTHER_COMPANY_LOGO)
        }
    }

}