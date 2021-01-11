package android.com.roshchupkin.unsplashapp.database.mapper

import android.com.roshchupkin.unsplashapp.database.entity.RandomImageCacheEntity
import android.com.roshchupkin.unsplashapp.model.Image.User
import android.com.roshchupkin.unsplashapp.utill.EntityMapper
import javax.inject.Inject

class UserCacheMapper
@Inject
constructor(private val profileImageCacheMapper: ProfileImageCacheMapper):EntityMapper<RandomImageCacheEntity, User>{
    override fun mapToEntity(domainModule: User): RandomImageCacheEntity {
        TODO("Not yet implemented")
    }

    override fun mapFromEntity(entity: RandomImageCacheEntity): User {
        return User(
            name = null,
            username = entity.username,
            profile_image = profileImageCacheMapper.mapFromEntity(entity)
        )
    }
}