package android.com.roshchupkin.unsplashapp.database.mapper

import android.com.roshchupkin.unsplashapp.database.entity.RandomImageCacheEntity

import android.com.roshchupkin.unsplashapp.network.entity.UserNetworkEntity
import android.com.roshchupkin.unsplashapp.utill.EntityMapper
import javax.inject.Inject

class UserCacheMapper
@Inject
constructor():EntityMapper<RandomImageCacheEntity,UserNetworkEntity>{
    override fun mapToEntity(domainModule: UserNetworkEntity): RandomImageCacheEntity {
        TODO("Not yet implemented")
    }

    override fun mapFromEntity(entity: RandomImageCacheEntity): UserNetworkEntity {
        return UserNetworkEntity(
            name = null,
            username = entity.username
        )
    }
}