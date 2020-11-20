package com.ayder.typicalapp.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
data class MockUser(val id: Int,
                    val name: String,
                    var avatarUrl: String,
                    val creationDate: Date) : Parcelable