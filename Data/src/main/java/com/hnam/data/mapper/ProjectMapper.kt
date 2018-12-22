package com.hnam.data.mapper

import com.hnam.data.model.ProjectEntity
import com.hnam.domain.model.Project
import javax.inject.Inject

/**
 * Created by nampham on 12/12/18.
 */
open class ProjectMapper  @Inject constructor(): EntityMapper<ProjectEntity, Project> {
    override fun mapFromEntity(entity: ProjectEntity): Project {
        return Project(entity.id, entity.name, entity.fullName,
            entity.starCount, entity.dateCreated, entity.ownerName, entity.ownerAvatar, entity.bookMarked)
    }

    override fun mapToEntity(domain: Project): ProjectEntity {
        return ProjectEntity(domain.id, domain.name, domain.fullName, domain.starCount, domain.dateCreated,
            domain.ownerName, domain.ownerAvatar, domain.bookMarked)
    }
}