package com.ayder.typicalapp.data.room.util

import com.ayder.typicalapp.data.model.NasaDailyPicture
import com.ayder.typicalapp.data.room.entity.NasaDailyPictureCacheEntity
import com.ayder.typicalapp.data.utils.ModelMapper
import javax.inject.Inject

class NasaDailyPictureCacheMapper
@Inject
constructor() : ModelMapper<NasaDailyPictureCacheEntity, NasaDailyPicture> {
    override fun toModel(entity: NasaDailyPictureCacheEntity): NasaDailyPicture {
        return NasaDailyPicture(
                title = entity.title,
                copyright = entity.copyright,
                date = entity.date,
                url = entity.url,
                hdurl = entity.hdurl,
                explanation = entity.explanation
        )
    }

    override fun fromModel(model: NasaDailyPicture): NasaDailyPictureCacheEntity {
        return NasaDailyPictureCacheEntity(
                title = model.title,
                copyright = model.copyright,
                date = model.date,
                url = model.url,
                hdurl = model.hdurl,
                explanation = model.explanation
        )
    }
}