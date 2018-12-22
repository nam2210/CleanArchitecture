package com.hnam.cleanarchitecture.browse

/**
 * Created by nampham on 12/22/18.
 */
interface ProjectListener {
    fun onBookmarkedProjectClicked(projectId: String)

    fun onProjectClicked(projectId: String)

}