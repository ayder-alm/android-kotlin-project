package com.ayder.typicalapp.data.retrofit.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class MockApiUserEntity(
        @Expose
        @SerializedName("id")
        var id: Int,

        @Expose
        @SerializedName("createdAt")
        var creationDate: String,

        @Expose
        @SerializedName("name")
        var name: String,

        @Expose
        @SerializedName("avatar")
        var avatarUrl: String
)