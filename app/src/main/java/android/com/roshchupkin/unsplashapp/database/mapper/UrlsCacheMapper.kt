package android.com.roshchupkin.unsplashapp.database.mapper


import android.com.roshchupkin.unsplashapp.database.entity.RandomImageCacheEntity
import android.com.roshchupkin.unsplashapp.network.entity.UrlsNetworkEntity
import android.com.roshchupkin.unsplashapp.utill.EntityMapper

import javax.inject.Inject

class UrlsCacheMapper
@Inject
constructor() : EntityMapper<RandomImageCacheEntity, UrlsNetworkEntity> {
    override fun mapToEntity(domainModule: UrlsNetworkEntity): RandomImageCacheEntity {
        TODO("Not yet implemented")
    }

    override fun mapFromEntity(entity: RandomImageCacheEntity): UrlsNetworkEntity {
        return UrlsNetworkEntity(
            full = null,
            raw = null,
            regular = entity.urlsImageRegular,
            small = null,
            thumb = null
        )
    }
}