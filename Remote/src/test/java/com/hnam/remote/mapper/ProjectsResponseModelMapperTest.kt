package com.hnam.remote.mapper

import com.hnam.remote.factory.ProjectDataFactory
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import kotlin.test.assertEquals

/**
 * Created by nampham on 12/16/18.
 */
@RunWith(JUnit4::class)
class ProjectsResponseModelMapperTest {

    private val mapper = ProjectsResponseModelMapper()

    @Test
    fun mapFromModelMapsData(){
        val model = ProjectDataFactory.makeProject()
        val entity = mapper.mapFromModel(model)

        assertEquals(model.id, entity.id)
        assertEquals(model.name, entity.name)
        assertEquals(model.fullName, entity.fullName)
        assertEquals(model.starCount.toString(), entity.starCount)
        assertEquals(model.dateCreated, entity.dateCreated)
        assertEquals(model.owner.ownerName, entity.ownerName)
        assertEquals(model.owner.ownerAvatar, entity.ownerAvatar)

    }
}