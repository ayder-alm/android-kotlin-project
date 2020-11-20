package com.ayder.typicalapp.data.room.util

import com.ayder.typicalapp.data.model.MockUser
import com.ayder.typicalapp.data.room.entity.MockUserCacheEntity
import com.ayder.typicalapp.data.utils.ModelMapper
import javax.inject.Inject

class MockUserCacheMapper
@Inject
constructor() : ModelMapper<MockUserCacheEntity, MockUser>
{
    override fun toModel(entity: MockUserCacheEntity): MockUser {
        return MockUser(
                id = entity.id,
                name = entity.name,
                avatarUrl = entity.avatarUrl,
                creationDate = entity.creationDate
        )
    }

    override fun fromModel(model: MockUser): MockUserCacheEntity {
        return MockUserCacheEntity(
                id = model.id,
                name = model.name,
                avatarUrl = model.avatarUrl,
                creationDate = model.creationDate
        )
    }
}