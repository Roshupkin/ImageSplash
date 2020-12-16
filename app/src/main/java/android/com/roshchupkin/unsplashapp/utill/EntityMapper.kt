package android.com.roshchupkin.unsplashapp.utill

interface EntityMapper<Entity, DomainModule> {
    fun mapToEntity(domainModule: DomainModule): Entity
    fun mapFromEntity(entity: Entity): DomainModule
}