package com.hnam.cleanarchitecture.mapper

import com.hnam.cleanarchitecture.model.Project
import com.hnam.presentation.model.ProjectView
import javax.inject.Inject

/**
 * Created by nampham on 12/21/18.
 */
class ProjectViewMapper @Inject constructor(): ViewMapper<ProjectView, Project> {

    override fun mapToView(presentation: ProjectView): Project {
        return Project(presentation.id, presentation.name,
            presentation.fullName, presentation.starCount,
            presentation.dateCreated, presentation.ownerName,
            presentation.ownerAvatar, presentation.isBookmarked)
    }

}