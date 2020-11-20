package com.ayder.typicalapp.data.retrofit.util

import com.ayder.typicalapp.data.model.NasaDailyPicture
import com.ayder.typicalapp.data.retrofit.entity.DailyPictureEntity
import com.ayder.typicalapp.data.utils.ModelMapper
import javax.inject.Inject

class NasaDailyPictureNetworkMapper
@Inject
constructor() : ModelMapper<DailyPictureEntity, NasaDailyPicture> {
    override fun toModel(entity: DailyPictureEntity): NasaDailyPicture {
        return NasaDailyPicture(
                title = entity.title,
                copyright = entity.copyright,
                date = entity.date,
                url = entity.url,
                hdurl = entity.hdurl,
                explanation = entity.explanation
        )
    }

    override fun fromModel(model: NasaDailyPicture): DailyPictureEntity {
        return DailyPictureEntity(
                title = model.title,
                copyright = model.copyright,
                date = model.date,
                url = model.url,
                hdurl = model.hdurl,
                explanation = model.explanation,
        )
    }

}