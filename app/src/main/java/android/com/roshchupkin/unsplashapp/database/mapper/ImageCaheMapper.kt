package android.com.roshchupkin.unsplashapp.database.mapper


import android.com.roshchupkin.unsplashapp.database.entity.RandomImageCacheEntity
import android.com.roshchupkin.unsplashapp.model.Image.ImageDomain
import android.com.roshchupkin.unsplashapp.utill.EntityMapper
import javax.inject.Inject

class ImageCacheMapper
@Inject
constructor(
    private val urlsCacheMapper: UrlsCacheMapper,
    private val userCacheMapper: UserCacheMapper
) : EntityMapper<RandomImageCacheEntity, ImageDomain> {

    override fun mapToEntity(domainModule: ImageDomain): RandomImageCacheEntity {
        return RandomImageCacheEntity(
            id = domainModule.id,
            description = domainModule.description,
            height = domainModule.height,
            width = domainModule.width,
            urlsImageRegular = domainModule.urls?.regular,
            username = domainModule.user?.username,
            profileImageSmall = domainModule.user?.profile_image?.small
        )
    }

    override fun mapFromEntity(entity: RandomImageCacheEntity): ImageDomain {
        return ImageDomain(
            id = entity.id,
            description = entity.description,
            height = entity.height,
            width = entity.width,
            urls = urlsCacheMapper.mapFromEntity(entity),
            user = userCacheMapper.mapFromEntity(entity)
        )
    }

    fun mapToEntityList(domainModels: List<ImageDomain>): List<RandomImageCacheEntity> {
        return domainModels.map { mapToEntity(it) }
    }

    fun mapFromEntityList(entities: List<RandomImageCacheEntity>): List<ImageDomain> {
        return entities.map { mapFromEntity(it) }
    }
}