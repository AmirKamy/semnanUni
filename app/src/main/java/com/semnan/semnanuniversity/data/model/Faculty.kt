package com.semnan.semnanuniversity.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

data class Faculty(
    val id: String,
    val title: String,
    val body: String,
    val imageRes: Int,
    val phone: String,
    val map: String,
    val siteAddress: String
)

