package com.hnam.presentation.mapper

import com.hnam.domain.model.Project
import com.hnam.presentation.model.ProjectView
import javax.inject.Inject

/**
 * Created by nampham on 12/17/18.
 */
open class ProjectViewMapper @Inject constructor() : Mapper<ProjectView, Project> {

    override fun mapToView(type: Project): ProjectView {
        return ProjectView(type.id, type.name, type.fullName,
            type.starCount, type.dateCreated, type.ownerName,
            type.ownerAvatar, type.bookMarked)
    }
}