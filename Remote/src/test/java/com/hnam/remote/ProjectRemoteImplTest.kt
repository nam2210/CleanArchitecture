package com.hnam.remote

import com.hnam.data.model.ProjectEntity
import com.hnam.remote.data.ProjectModel
import com.hnam.remote.data.ProjectsResponseModel
import com.hnam.remote.factory.ProjectDataFactory
import com.hnam.remote.mapper.ProjectsResponseModelMapper
import com.hnam.remote.services.GithubTrendingService
import com.nhaarman.mockitokotlin2.*
import io.reactivex.Observable
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

/**
 * Created by nampham on 12/16/18.
 */
@RunWith(JUnit4::class)
class ProjectRemoteImplTest {

    private val service  = mock<GithubTrendingService>()
    private val mapper = mock<ProjectsResponseModelMapper>()

    private val remote = ProjectsRemoteImpl(service, mapper)

    //test that function work right or not
    @Test
    fun getProjectCompletes(){
        stubGithubTrendingServiceSearchRepository(Observable.just(ProjectDataFactory.makeProjectsResponse()))
        stubProjectsResponseModelMapperFromModel(any(), ProjectDataFactory.makeProjectEntity())
        val observable = remote.getProjects().test()
        observable.assertComplete()
    }

    //test function is invoked or not
    @Test
    fun getProjectCallServer(){
        stubGithubTrendingServiceSearchRepository(Observable.just(ProjectDataFactory.makeProjectsResponse()))
        stubProjectsResponseModelMapperFromModel(any(), ProjectDataFactory.makeProjectEntity())

        remote.getProjects()
        verify(remote.service).searchRepositories(any(), any(), any())
    }

    //test function return right data or not
    @Test
    fun getProjectsReturnData(){
        val response = ProjectDataFactory.makeProjectsResponse()
        stubGithubTrendingServiceSearchRepository(Observable.just(response))
        val entities = mutableListOf<ProjectEntity>()
        response.items.forEach{
            val entity = ProjectDataFactory.makeProjectEntity()
            entities.add(entity)
            stubProjectsResponseModelMapperFromModel(it, entity)
        }

        val observable = remote.getProjects().test()
        observable.assertValue(entities)
    }

    @Test
    fun getProjectCallServerWithCorrectParams(){
        stubGithubTrendingServiceSearchRepository(Observable.just(ProjectDataFactory.makeProjectsResponse()))
        stubProjectsResponseModelMapperFromModel(any(), ProjectDataFactory.makeProjectEntity())

        remote.getProjects()
        verify(remote.service).searchRepositories("language:kotlin", "stars", "desc")
    }

    private fun stubGithubTrendingServiceSearchRepository(observable: Observable<ProjectsResponseModel>){
        whenever(service.searchRepositories(any(), any(), any())).thenReturn(observable)
    }

    private fun stubProjectsResponseModelMapperFromModel(model : ProjectModel, entity: ProjectEntity){
        whenever(mapper.mapFromModel(model)).thenReturn(entity)
    }
}