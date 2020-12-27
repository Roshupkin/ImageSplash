package android.com.roshchupkin.unsplashapp.database.mapper

import android.com.roshchupkin.unsplashapp.database.entity.RandomImageCacheEntity
import android.com.roshchupkin.unsplashapp.model.RandomImage
import android.com.roshchupkin.unsplashapp.utill.EntityMapper
import javax.inject.Inject

class RandomImageCacheMapper
@Inject
constructor(
    private val urlsCacheMapper: UrlsCacheMapper,
    private val userCacheMapper: UserCacheMapper
) : EntityMapper<RandomImageCacheEntity, RandomImage> {

    override fun mapToEntity(domainModule: RandomImage): RandomImageCacheEntity {
        return RandomImageCacheEntity(
            id = domainModule.id,
            description = domainModule.description,
            height = domainModule.height,
            width = domainModule.width,
            urlsImageFull = domainModule.urls.full,
            username = domainModule.user.username
        )
    }

    override fun mapFromEntity(entity: RandomImageCacheEntity): RandomImage {
        return RandomImage(
            id = entity.id,
            description = entity.description,
            height = entity.height,
            width = entity.width,
            urls = urlsCacheMapper.mapFromEntity(entity),
            user = userCacheMapper.mapFromEntity(entity)
        )
    }
}