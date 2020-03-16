package com.aba.core.data.mapper

import com.aba.core.base.ResultResponse
import com.aba.core.data.model.JobItem
import com.aba.core.domain.data.JobDomainModel

class JobMapper {
    fun map(input: ResultResponse): ResultResponse =
        with((input as ResultResponse.Success<List<JobItem>>).data) {
            val domainModels = mutableListOf<JobDomainModel>()
            this.forEach {
                domainModels.add(
                    JobDomainModel(
                        id = it.id,
                        company = it.company,
                        companyLogo = it.companyLogo ?: "",
                        companyUrl = it.companyUrl ?: "",
                        description = it.description,
                        howToApply = it.howToApply,
                        location = it.location ?: "",
                        title = it.title,
                        url = it.url ?: ""
                    )
                )
            }
            ResultResponse.Success(domainModels)
        }
}