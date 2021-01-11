package android.com.roshchupkin.unsplashapp.network.mapper

import android.com.roshchupkin.unsplashapp.model.Image.ImageDomain
import android.com.roshchupkin.unsplashapp.network.entity.image.ImageNetworkEntity
import android.com.roshchupkin.unsplashapp.utill.EntityMapper
import javax.inject.Inject

class ImageNetworkMapper @Inject
constructor(
    private val urlsNetworkMapper: UrlsNetworkMapper,
    private val userNetworkMapper: UserNetworkMapper
) : EntityMapper<ImageNetworkEntity, ImageDomain> {

    override fun mapToEntity(domainModule: ImageDomain): ImageNetworkEntity {
        return ImageNetworkEntity(
            id = domainModule.id,
            description = domainModule.description,
            height = domainModule.height,
            width = domainModule.width,
            urls = domainModule.urls?.let { urlsNetworkMapper.mapToEntity(domainModule = it) },
            user = domainModule.user?.let { userNetworkMapper.mapToEntity(domainModule = it) })
    }

    override fun mapFromEntity(entity: ImageNetworkEntity): ImageDomain {
        return ImageDomain(
            id = entity.id,
            description = entity.description,
            height = entity.height,
            width = entity.width,
            urls = entity.urls?.let { urlsNetworkMapper.mapFromEntity(entity =  it) },
            user = entity.user?.let { userNetworkMapper.mapFromEntity(entity = it) }
        )
    }

    fun mapToEntityList(domainModels: List<ImageDomain>): List<ImageNetworkEntity> {
        return domainModels.map { mapToEntity(it) }
    }

    fun mapFromEntityList(entities: List<ImageNetworkEntity>): List<ImageDomain> {
        return entities.map { mapFromEntity(it) }
    }
}