package android.com.roshchupkin.unsplashapp.network.mapper

import android.com.roshchupkin.unsplashapp.model.ProfileImage
import android.com.roshchupkin.unsplashapp.network.entity.ProfileImageNetworkEntity
import android.com.roshchupkin.unsplashapp.utill.EntityMapper
import javax.inject.Inject

class ProfileImageNetworkMapper
@Inject
constructor() : EntityMapper<ProfileImageNetworkEntity, ProfileImage> {
    override fun mapToEntity(domainModule: ProfileImage): ProfileImageNetworkEntity {
        return ProfileImageNetworkEntity(
            small = domainModule.small,
            medium = domainModule.medium,
            large = domainModule.large
        )
    }

    override fun mapFromEntity(entity: ProfileImageNetworkEntity): ProfileImage {
       return ProfileImage(
           small = entity.small,
           medium = entity.medium,
           large = entity.large
       )
    }
}