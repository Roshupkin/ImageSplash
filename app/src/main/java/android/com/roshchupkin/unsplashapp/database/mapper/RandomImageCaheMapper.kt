package android.com.roshchupkin.unsplashapp.database.mapper


import android.com.roshchupkin.unsplashapp.database.entity.RandomImageCacheEntity
import android.com.roshchupkin.unsplashapp.network.entity.RandomImageNetworkEntity
import android.com.roshchupkin.unsplashapp.utill.EntityMapper
import androidx.paging.PagingData
import javax.inject.Inject

class RandomImageCacheMapper
@Inject
constructor(
    private val urlsCacheMapper: UrlsCacheMapper,
    private val userCacheMapper: UserCacheMapper
) : EntityMapper<RandomImageCacheEntity, RandomImageNetworkEntity> {

    override fun mapToEntity(domainModule: RandomImageNetworkEntity): RandomImageCacheEntity {
        return RandomImageCacheEntity(
            id = domainModule.id,
            description = domainModule.description,
            height = domainModule.height,
            width = domainModule.width,
            urlsImageRegular = domainModule.urls?.regular,
            username = domainModule.user?.username
        )
    }

    override fun mapFromEntity(entity: RandomImageCacheEntity): RandomImageNetworkEntity {
        return RandomImageNetworkEntity(
            id = entity.id,
            description = entity.description,
            height = entity.height,
            width = entity.width,
            urls = urlsCacheMapper.mapFromEntity(entity),
            user = userCacheMapper.mapFromEntity(entity)
        )
    }

    fun mapToEntityList(domainModels: List<RandomImageNetworkEntity>): List<RandomImageCacheEntity> {
        return domainModels.map { mapToEntity(it) }
    }

    fun mapFromEntityList(entities: List<RandomImageCacheEntity>): List<RandomImageNetworkEntity> {
        return entities.map { mapFromEntity(it) }
    }
}