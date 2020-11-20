package com.ayder.typicalapp.data.retrofit.util

import com.ayder.typicalapp.data.model.MockUser
import com.ayder.typicalapp.data.retrofit.entity.MockApiUserEntity
import com.ayder.typicalapp.data.utils.ModelMapper
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

private val DATE_FORMAT = SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss.SSSX", Locale.ENGLISH)

class MockUserNetworkMapper
@Inject
constructor() : ModelMapper<MockApiUserEntity, MockUser> {
    override fun toModel(entity: MockApiUserEntity): MockUser {
        var date: Date? = null
        try {
            date = DATE_FORMAT.parse(entity.creationDate)
        } catch (e: ParseException) {
            println(e.localizedMessage)
        } finally {
            if (date == null) {
                date = Date()
            }
        }
        return MockUser(id = entity.id, name = entity.name, avatarUrl = entity.avatarUrl, creationDate = date)
    }

    override fun fromModel(model: MockUser): MockApiUserEntity {
        val dateString = DATE_FORMAT.format(model.creationDate)
        return MockApiUserEntity(
                0,
                creationDate = dateString,
                name = model.name,
                avatarUrl = model.avatarUrl
        )
    }
}