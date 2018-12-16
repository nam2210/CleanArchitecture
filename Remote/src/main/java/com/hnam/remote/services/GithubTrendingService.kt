package com.hnam.remote.services

import com.hnam.remote.data.ProjectsResponseModel
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by nampham on 12/16/18.
 */
interface GithubTrendingService {
    @GET("search/repositories")
    fun searchRepositories(@Query("q") query: String,
                           @Query("sort") sortBy: String,
                           @Query("order") order: String)
            : Observable<ProjectsResponseModel>
}