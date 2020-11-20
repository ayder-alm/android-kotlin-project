package com.ayder.typicalapp.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class NasaDailyPicture(
        var title: String?,
        var copyright: String?,
        var date: String,
        var url: String,
        var hdurl: String?,
        var explanation: String?,
) : Parcelable {
    override fun equals(other: Any?): Boolean {
        return other is NasaDailyPicture
                && title == other.title
                && copyright == other.copyright
                && date == other.date
                && url == other.url
                && hdurl == other.hdurl
                && explanation == other.explanation

    }
}