package android.com.roshchupkin.unsplashapp.network.mapper

import android.com.roshchupkin.unsplashapp.model.Image.User
import android.com.roshchupkin.unsplashapp.network.entity.image.UserNetworkEntity
import android.com.roshchupkin.unsplashapp.utill.EntityMapper
import javax.inject.Inject

class UserNetworkMapper @Inject
constructor(private val profileImageNetworkMapper: ProfileImageNetworkMapper) :
    EntityMapper<UserNetworkEntity, User> {
    override fun mapToEntity(domainModule: User): UserNetworkEntity {
        return UserNetworkEntity(
            name = domainModule.name,
            username = domainModule.username,
            profile_image = domainModule.profile_image?.let {
                profileImageNetworkMapper.mapToEntity(it)
            }
        )
    }

    override fun mapFromEntity(entity: UserNetworkEntity): User {
        return User(
            name = entity.name,
            username = entity.username,
            profile_image = entity.profile_image?.let { profileImageNetworkMapper.mapFromEntity(it) }
        )
    }
}