package android.com.roshchupkin.unsplashapp.database.mapper


import android.com.roshchupkin.unsplashapp.database.entity.RandomImageCacheEntity
import android.com.roshchupkin.unsplashapp.model.ImageDomain
import android.com.roshchupkin.unsplashapp.model.ProfileImage
import android.com.roshchupkin.unsplashapp.model.Urls
import android.com.roshchupkin.unsplashapp.model.User
import android.com.roshchupkin.unsplashapp.utill.EntityMapper
import javax.inject.Inject

class ImageCacheMapper
@Inject
constructor() : EntityMapper<RandomImageCacheEntity, ImageDomain> {

    override fun mapToEntity(domainModule: ImageDomain): RandomImageCacheEntity {
        return RandomImageCacheEntity(
            id = domainModule.id,
            urlsImageRegular = domainModule.urls?.regular,
            name = domainModule.user?.name,
            profileImageSmall = domainModule.user?.profile_image?.small
        )
    }

    override fun mapFromEntity(entity: RandomImageCacheEntity): ImageDomain {
        return ImageDomain(
            id = entity.id,
            description = null,
            height = null,
            width = null,
            urls = mapToUrls(entity),
            user = mapToUser(entity)
        )
    }

    fun mapToUrls(entity: RandomImageCacheEntity): Urls {
        return Urls(
            full = null,
            raw = null,
            regular = entity.urlsImageRegular,
            small = null,
            thumb = null
        )
    }

    fun mapToUser(entity: RandomImageCacheEntity): User {
        return User(
            name = entity.name,
            username = null,
            profile_image = mapToProfileImage(entity)
        )
    }

    fun mapToProfileImage(entity: RandomImageCacheEntity): ProfileImage {
        return ProfileImage(
            small = entity.profileImageSmall,
            medium = null,
            large = null
        )
    }

    fun mapToEntityList(domainModels: List<ImageDomain>): List<RandomImageCacheEntity> {
        return domainModels.map { mapToEntity(it) }
    }

    fun mapFromEntityList(entities: List<RandomImageCacheEntity>): List<ImageDomain> {
        return entities.map { mapFromEntity(it) }
    }

}