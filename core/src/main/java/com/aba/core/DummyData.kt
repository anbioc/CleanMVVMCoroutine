package com.aba.core

import com.aba.core.data.model.JobItem
import com.aba.core.domain.data.JobDomainModel
import com.aba.core.domain.usecase.JobUseCase
import java.util.*

const val SOME_URL = "SOME_URL"
const val SOME_TYPE = "SOME_TYPE"
const val SOME_TITLE = "SOME_TITLE"
const val SOME_ID = "SOME_ID"
const val SOME_HOW_TO_APPLY = "SOME_HOW_TO_APPLY"
const val SOME_CREATED_AT = "SOME_CREATED_AT"
const val SOME_COMPANY_URL = "SOME_COMPANY_URL"
const val SOME_COMPANY_LOGO = "SOME_COMPANY_LOGO"
const val SOME_COMPANY = "SOME_COMPANY"
const val SOME_DESCRIPTION = "SOME_DESCRIPTION"
const val SOME_LOCATION = "SOME_LOCATION"

const val SOME_OTHER_URL = "SOME_OTHER_URL"
const val SOME_OTHER_TYPE = "SOME_OTHER_TYPE"
const val SOME_OTHER_TITLE = "SOME_TOTHER_ITLE"
const val SOME_OTHER_ID = "SOME_OTHER_ID"
const val SOME_OTHER_HOW_TO_APPLY = "SOME_OTHER_HOW_TO_APPLY"
const val SOME_OTHER_CREATED_AT = "SOME_OTHER_CREATED_AT"
const val SOME_OTHER_COMPANY_URL = "SOME_OTHER_COMPANY_URL"
const val SOME_OTHER_COMPANY_LOGO = "SOME_OTHER_COMPANY_LOGO"
const val SOME_OTHER_COMPANY = "SOME_OTHER_COMPANY"
const val SOME_OTHER_DESCRIPTION = "SOME_OTHER_DESCRIPTION"
const val SOME_OTHER_LOCATION = "SOME_OTHER_LOCATION"


val SOME_JOB_RESPONSE = JobItem().apply {
    company = SOME_COMPANY
    companyLogo = SOME_COMPANY_LOGO
    companyUrl = SOME_COMPANY_URL
    createdAt = SOME_CREATED_AT
    description = SOME_DESCRIPTION
    howToApply = SOME_HOW_TO_APPLY
    id = SOME_ID
    location = SOME_LOCATION
    title = SOME_TITLE
    type = SOME_TYPE
    url = SOME_URL
}

val SOME_OTHER_JOB_RESPONSE_WITH_SAME_ID = JobItem().apply {
    company = SOME_OTHER_COMPANY
    companyLogo = SOME_OTHER_COMPANY_LOGO
    companyUrl = SOME_OTHER_COMPANY_URL
    createdAt = SOME_OTHER_CREATED_AT
    description = SOME_OTHER_DESCRIPTION
    howToApply = SOME_OTHER_HOW_TO_APPLY
    id = SOME_ID
    location = SOME_OTHER_LOCATION
    title = SOME_OTHER_TITLE
    type = SOME_OTHER_TYPE
    url = SOME_OTHER_URL
}

val SOME_OTHER_JOB_RESPONSE = JobItem().apply {
    company = SOME_OTHER_COMPANY
    companyLogo = SOME_OTHER_COMPANY_LOGO
    companyUrl = SOME_OTHER_COMPANY_URL
    createdAt = SOME_OTHER_CREATED_AT
    description = SOME_OTHER_DESCRIPTION
    howToApply = SOME_OTHER_HOW_TO_APPLY
    id = SOME_OTHER_ID
    location = SOME_OTHER_LOCATION
    title = SOME_OTHER_TITLE
    type = SOME_OTHER_TYPE
    url = SOME_OTHER_URL
}


val SOME_JOB_DOMAIN_MODEL = JobDomainModel(
    company = SOME_COMPANY,
    companyLogo = SOME_COMPANY_LOGO,
    companyUrl = SOME_COMPANY_URL,
    description = SOME_DESCRIPTION,
    howToApply = SOME_HOW_TO_APPLY,
    id = SOME_ID,
    location = SOME_LOCATION,
    title = SOME_TITLE,
    url = SOME_URL
)

val SOME_OTHER_JOB_DOMAIN_MODEL = JobDomainModel(
    company = SOME_OTHER_COMPANY,
    companyLogo = SOME_OTHER_COMPANY_LOGO,
    companyUrl = SOME_OTHER_COMPANY_URL,
    description = SOME_OTHER_DESCRIPTION,
    howToApply = SOME_OTHER_HOW_TO_APPLY,
    id = SOME_OTHER_ID,
    location = SOME_OTHER_LOCATION,
    title = SOME_OTHER_TITLE,
    url = SOME_OTHER_URL
)

val SOME_JOB_DOMAIN_MODELS = Collections.singletonList(SOME_JOB_DOMAIN_MODEL)
val SOME_OTHER_JOB_DOMAIN_MODELS = Collections.singletonList(SOME_OTHER_JOB_DOMAIN_MODEL)

val SOME_JOB_RESPONSE_ITEMS: MutableList<JobItem> = Collections.singletonList(SOME_JOB_RESPONSE)
val SOME_OTHER_JOB_RESPONSE_ITEMS_SAME_ID: MutableList<JobItem> =
    Collections.singletonList(SOME_OTHER_JOB_RESPONSE_WITH_SAME_ID)
val SOME_OTHER_JOB_RESPONSE_ITEMS = listOf(SOME_OTHER_JOB_RESPONSE)

val JOB_USE_CASE_PARAMS = JobUseCase.JobUseCaseParams(SOME_DESCRIPTION, SOME_LOCATION)

const val SOME_MESSAGE = "SOME_MESSAGE"
