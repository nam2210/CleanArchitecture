package com.hnam.data.store




import com.nhaarman.mockitokotlin2.mock
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.mock
import org.mockito.MockitoAnnotations

import kotlin.test.assertEquals

/**
 * Created by nampham on 12/13/18.
 */
class ProjectsDataStoreFactoryTest {

    private val cache = mock<ProjectCacheDataStore>()
    private val remote = mock<ProjectRemoteDataStore>()
    private val factory = ProjectDataStoreFactory(cache, remote)

//    @Mock lateinit var cache: ProjectCacheDataStore
//
//    @Mock
//    lateinit var remote: ProjectRemoteDataStore
//
//    private lateinit var factory: ProjectDataStoreFactory
//
//    @Before
//    fun setup(){
//        MockitoAnnotations.initMocks(this)
//        factory = ProjectDataStoreFactory(cache, remote)
//    }

    @Test
    fun getDataStoreReturnRemoteStoreWhenCacheExpire(){
        assertEquals(remote, factory.getDataStore(true, true))
    }
}