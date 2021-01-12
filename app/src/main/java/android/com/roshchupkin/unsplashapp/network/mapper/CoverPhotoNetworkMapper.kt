package android.com.roshchupkin.unsplashapp.network.mapper

import android.com.roshchupkin.unsplashapp.model.CoverPhoto
import android.com.roshchupkin.unsplashapp.network.entity.CoverPhotoNetworkEntity
import android.com.roshchupkin.unsplashapp.utill.EntityMapper
import javax.inject.Inject


class CoverPhotoNetworkMapper @Inject constructor(private val urlsNetworkMapper: UrlsNetworkMapper) :
    EntityMapper<CoverPhotoNetworkEntity, CoverPhoto> {
    override fun mapToEntity(domainModule: CoverPhoto): CoverPhotoNetworkEntity {
        return CoverPhotoNetworkEntity(
            urls = domainModule.urls?.let { urlsNetworkMapper.mapToEntity(it) }
        )
    }

    override fun mapFromEntity(entity: CoverPhotoNetworkEntity): CoverPhoto {
        return CoverPhoto(
            urls = entity.urls?.let { urlsNetworkMapper.mapFromEntity(it) }
        )
    }
}