package android.com.roshchupkin.unsplashapp.network.mapper

import android.com.roshchupkin.unsplashapp.database.entity.RandomImageCacheEntity
import android.com.roshchupkin.unsplashapp.database.mapper.UrlsCacheMapper
import android.com.roshchupkin.unsplashapp.database.mapper.UserCacheMapper
import android.com.roshchupkin.unsplashapp.model.ImageDomain
import android.com.roshchupkin.unsplashapp.network.entity.RandomImageNetworkEntity
import android.com.roshchupkin.unsplashapp.utill.EntityMapper
import javax.inject.Inject

class ImageNetworkMapper @Inject
constructor(
    private val urlsNetworkMapper: UrlsNetworkMapper,
    private val userNetworkMapper: UserNetworkMapper
) : EntityMapper<RandomImageNetworkEntity, ImageDomain> {

    override fun mapToEntity(domainModule: ImageDomain): RandomImageNetworkEntity {
        return RandomImageNetworkEntity(
            id = domainModule.id,
            description = domainModule.description,
            height = domainModule.height,
            width = domainModule.width,
            urls = domainModule.urls?.let { urlsNetworkMapper.mapToEntity(domainModule = it) },
            user = domainModule.user?.let { userNetworkMapper.mapToEntity(domainModule = it) })
    }

    override fun mapFromEntity(entity: RandomImageNetworkEntity): ImageDomain {
        return ImageDomain(
            id = entity.id,
            description = entity.description,
            height = entity.height,
            width = entity.width,
            urls = entity.urls?.let { urlsNetworkMapper.mapFromEntity(entity =  it) },
            user = entity.user?.let { userNetworkMapper.mapFromEntity(entity = it) }
        )
    }

    fun mapToEntityList(domainModels: List<ImageDomain>): List<RandomImageNetworkEntity> {
        return domainModels.map { mapToEntity(it) }
    }

    fun mapFromEntityList(entities: List<RandomImageNetworkEntity>): List<ImageDomain> {
        return entities.map { mapFromEntity(it) }
    }
}