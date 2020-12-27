package android.com.roshchupkin.unsplashapp.database.mapper

import android.com.roshchupkin.unsplashapp.database.entity.RandomImageCacheEntity
import android.com.roshchupkin.unsplashapp.model.Urls
import android.com.roshchupkin.unsplashapp.utill.EntityMapper

import javax.inject.Inject

class UrlsCacheMapper
@Inject
constructor() : EntityMapper<RandomImageCacheEntity, Urls> {
    override fun mapToEntity(domainModule: Urls): RandomImageCacheEntity {
        TODO("Not yet implemented")
    }

    override fun mapFromEntity(entity: RandomImageCacheEntity): Urls {
        return Urls(
            full = entity.urlsImageFull,
            raw = null,
            regular = null,
            small = null,
            thumb = null
        )
    }
}