package android.com.roshchupkin.unsplashapp.network.mapper

import android.com.roshchupkin.unsplashapp.model.Urls
import android.com.roshchupkin.unsplashapp.network.entity.RandomImageNetworkEntity
import android.com.roshchupkin.unsplashapp.network.entity.UrlsNetworkEntity
import android.com.roshchupkin.unsplashapp.utill.EntityMapper
import javax.inject.Inject

class UrlsNetworkMapper @Inject
    constructor() : EntityMapper<UrlsNetworkEntity, Urls> {
    override fun mapToEntity(domainModule: Urls): UrlsNetworkEntity {
        return UrlsNetworkEntity(
            full = domainModule.full,
            raw = domainModule.raw,
            regular = domainModule.regular,
            small = domainModule.small,
            thumb = domainModule.thumb
        )
    }

    override fun mapFromEntity(entity: UrlsNetworkEntity): Urls {
        return Urls(
            full = entity.full,
            raw = entity.raw,
            regular = entity.regular,
            small = entity.small,
            thumb = entity.thumb
        )
    }
}