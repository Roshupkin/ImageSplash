package android.com.roshchupkin.unsplashapp.network.mapper

import android.com.roshchupkin.unsplashapp.model.CollectionDomain
import android.com.roshchupkin.unsplashapp.network.entity.CollectionNetworkEntity
import android.com.roshchupkin.unsplashapp.utill.EntityMapper


class CollectionNetworkMapper constructor(
    private val coverPhotoNetworkMapper: CoverPhotoNetworkMapper,
    private val userNetworkMapper: UserNetworkMapper
) : EntityMapper<CollectionNetworkEntity, CollectionDomain> {
    override fun mapToEntity(domainModule: CollectionDomain): CollectionNetworkEntity {
        return CollectionNetworkEntity(
            id = domainModule.id,
            title = domainModule.title,
            total_photos = domainModule.totalPhotos,
            cover_photo = domainModule.coverPhoto?.let { coverPhotoNetworkMapper.mapToEntity(it) },
            user = domainModule.user?.let { userNetworkMapper.mapToEntity(it) }
        )
    }

    override fun mapFromEntity(entity: CollectionNetworkEntity): CollectionDomain {
        return CollectionDomain(
            id = entity.id,
            title = entity.title,
            totalPhotos = entity.total_photos,
            coverPhoto = entity.cover_photo?.let { coverPhotoNetworkMapper.mapFromEntity(it) },
            user = entity.user?.let { userNetworkMapper.mapFromEntity(it) }
        )
    }
    fun mapToEntityList(domainModels: List<CollectionDomain>): List<CollectionNetworkEntity> {
        return domainModels.map { mapToEntity(it) }
    }

    fun mapFromEntityList(entities: List<CollectionNetworkEntity>): List<CollectionDomain> {
        return entities.map { mapFromEntity(it) }
}

}