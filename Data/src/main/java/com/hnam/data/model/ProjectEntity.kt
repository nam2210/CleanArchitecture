package com.hnam.data.model

/**
 * Created by nampham on 12/11/18.
 */
class ProjectEntity(val id: String, val name: String, val fullName: String,
                    val starCount: String, val dateCreated: String,
                    val ownerName: String, val ownerAvatar: String, val bookMarked: Boolean = false) {

}