package android.com.roshchupkin.unsplashapp.network.mapper

import android.com.roshchupkin.unsplashapp.model.SearchImages
import android.com.roshchupkin.unsplashapp.network.entity.SearchImagesNetworkEntity
import android.com.roshchupkin.unsplashapp.utill.EntityMapper

class SearchImagesNetworkMapper
constructor(
    private val imageNetworkMapper: ImageNetworkMapper
):EntityMapper<SearchImagesNetworkEntity,SearchImages> {
    override fun mapToEntity(domainModule: SearchImages): SearchImagesNetworkEntity {
        return SearchImagesNetworkEntity(
            total = domainModule.total,
            total_pages = domainModule.total_pages,
            results = imageNetworkMapper.mapToEntityList(domainModule.results)
        )
    }

    override fun mapFromEntity(entity: SearchImagesNetworkEntity): SearchImages {
       return SearchImages(
           total = entity.total,
           total_pages = entity.total_pages,
           results = imageNetworkMapper.mapFromEntityList(entity.results)
       )
    }
}