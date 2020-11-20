package com.ayder.typicalapp.data.utils

interface ModelMapper<Entity, Model> {

    fun toModel(entity: Entity): Model

    fun fromModel(model: Model): Entity

}