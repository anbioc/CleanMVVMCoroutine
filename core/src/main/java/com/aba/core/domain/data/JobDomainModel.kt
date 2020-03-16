package com.aba.core.domain.data

import android.os.Parcel
import android.os.Parcelable

data class JobDomainModel(
    val id: String = "",
    val company: String = "",
    val companyLogo: String = "",
    val companyUrl: String = "",
    val description: String = "",
    val howToApply: String = "",
    val location: String = "",
    val title: String = "",
    val url: String = ""
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(company)
        parcel.writeString(companyLogo)
        parcel.writeString(companyUrl)
        parcel.writeString(description)
        parcel.writeString(howToApply)
        parcel.writeString(location)
        parcel.writeString(title)
        parcel.writeString(url)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<JobDomainModel> {
        override fun createFromParcel(parcel: Parcel): JobDomainModel {
            return JobDomainModel(parcel)
        }

        override fun newArray(size: Int): Array<JobDomainModel?> {
            return arrayOfNulls(size)
        }
    }
}