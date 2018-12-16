package com.hnam.remote.data

import com.google.gson.annotations.SerializedName

/**
 * Created by nampham on 12/16/18.
 */
class ProjectModel(val id: String, val name: String,
                   @SerializedName("full_name") val fullName: String,
                   @SerializedName("stargazers_count") val starCount: Int,
                   @SerializedName("created_at") val dateCreated: String,
                   val owner: OwnerModel) {
}