package com.example.newsapp.data.utils

interface EntityMapper <Entity, DomainModel>{

    fun mapFromLocalEntity(entity: Entity): DomainModel

    fun mapToEntity(domainModel: DomainModel): Entity

}