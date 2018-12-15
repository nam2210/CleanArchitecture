package com.hnam.data.mapper

import com.hnam.data.model.ProjectEntity
import com.hnam.data.test.factory.ProjectFactory
import com.hnam.domain.model.Project
import org.junit.Test
import kotlin.test.assertEquals

/**
 * Created by nampham on 12/14/18.
 */
class ProjectMapperTest {

    private val projectMapper = ProjectMapper()

    @Test
    fun mapFromEntity(){
        val entity = ProjectFactory.makeProjectEntity()
        val model = projectMapper.mapFromEntity(entity)

        assertEqualData(entity, model)
    }

    @Test
    fun mapToModel(){
        val model = ProjectFactory.makeProject()
        val entity = projectMapper.mapToEntity(model)

        assertEqualData(entity, model)
    }

    private fun assertEqualData(entity: ProjectEntity, model: Project) {
        assertEquals(entity.id, model.id)
        assertEquals(entity.name, model.name)
        assertEquals(entity.fullName, model.fullName)
        assertEquals(entity.ownerAvatar, model.ownerAvatar)
        assertEquals(entity.ownerName, model.ownerName)
        assertEquals(entity.starCount, model.starCount)
        assertEquals(entity.dateCreated, model.dateCreated)
        assertEquals(entity.bookMarked, model.bookMarked)
    }
}