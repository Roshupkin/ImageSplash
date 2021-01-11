package android.com.roshchupkin.unsplashapp.database.mapper

import android.com.roshchupkin.unsplashapp.database.entity.RandomImageCacheEntity
import android.com.roshchupkin.unsplashapp.model.Image.ProfileImage
import android.com.roshchupkin.unsplashapp.utill.EntityMapper
import javax.inject.Inject

class ProfileImageCacheMapper
@Inject
constructor():EntityMapper<RandomImageCacheEntity,ProfileImage> {
    override fun mapToEntity(domainModule: ProfileImage): RandomImageCacheEntity {
        TODO("Not yet implemented")
    }

    override fun mapFromEntity(entity: RandomImageCacheEntity): ProfileImage {
        return ProfileImage(
            small = entity.profileImageSmall,
            medium = null,
            large = null
        )
    }
}