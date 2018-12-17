package com.hnam.cache.mapper

import com.hnam.cache.model.CachedProject
import com.hnam.data.model.ProjectEntity
import javax.inject.Inject

/**
 * Created by nampham on 12/17/18.
 */
class CachedProjectMapper @Inject constructor(): CacheMapper<CachedProject, ProjectEntity> {
    override fun mapFromCached(type: CachedProject): ProjectEntity {
        return ProjectEntity(type.id, type.name, type.fullName, type.starCount,
            type.dateCreated, type.ownerName, type.ownerAvatar,
            type.isBookmarked)
    }

    override fun mapToCached(type: ProjectEntity): CachedProject {
        return CachedProject(type.id, type.name, type.fullName, type.starCount,
            type.dateCreated, type.ownerName, type.ownerAvatar,
            type.bookMarked)
    }
}