package com.hnam.cache.mapper

import com.hnam.cache.model.CachedProject
import com.hnam.cache.test.factory.ProjectDataFactory
import com.hnam.data.model.ProjectEntity
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import kotlin.test.assertEquals

/**
 * Created by nampham on 12/17/18.
 */
@RunWith(JUnit4::class)
class CachedProjectMapperTest{

    private val mapper = CachedProjectMapper()

    @Test
    fun mapFromCached(){
        val cachedProject = ProjectDataFactory.makeCachedProject()
        val entity = mapper.mapFromCached(cachedProject)

        assertValue(cachedProject, entity)
    }

    @Test
    fun mapToCached(){
        val entity = ProjectDataFactory.makeProjectEntity()
        val cachedProject = mapper.mapToCached(entity)


        assertValue(cachedProject, entity)
    }



    private fun assertValue(cache: CachedProject, entity: ProjectEntity){
        assertEquals(cache.id, entity.id)
        assertEquals(cache.name, entity.name)
        assertEquals(cache.fullName, entity.fullName)
        assertEquals(cache.starCount, entity.starCount)
        assertEquals(cache.dateCreated, entity.dateCreated)
        assertEquals(cache.ownerName, entity.ownerName)
        assertEquals(cache.ownerAvatar, entity.ownerAvatar)
        assertEquals(cache.isBookmarked, entity.bookMarked)
    }
}