package com.hnam.remote.mapper

import com.hnam.data.model.ProjectEntity
import com.hnam.remote.data.ProjectModel
import javax.inject.Inject

/**
 * Created by nampham on 12/16/18.
 */
open class ProjectsResponseModelMapper @Inject constructor(): ModelMapper<ProjectModel, ProjectEntity>  {
    override fun mapFromModel(model: ProjectModel): ProjectEntity {
        return ProjectEntity(model.id, model.name, model.fullName,
            model.starCount.toString(), model.dateCreated, model.owner.ownerName,
            model.owner.ownerAvatar)
    }
}