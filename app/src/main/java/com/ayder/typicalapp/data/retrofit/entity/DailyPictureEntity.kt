package com.ayder.typicalapp.data.retrofit.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class DailyPictureEntity(
        @SerializedName("title")
        @Expose
        var title: String?,

        @SerializedName("copyright")
        @Expose
        var copyright: String?,

        @SerializedName("date")
        @Expose
        var date: String,

        @SerializedName("url")
        @Expose
        var url: String,

        @SerializedName("hdurl")
        @Expose
        var hdurl: String?,

        @SerializedName("explanation")
        @Expose
        var explanation: String?,

        @SerializedName("media_type")
        var media_type: String = "",

        @SerializedName("service_version")
        var service_version: String = ""
)