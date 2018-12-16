package com.hnam.remote.data

import com.google.gson.annotations.SerializedName

/**
 * Created by nampham on 12/16/18.
 */

class OwnerModel(@SerializedName("login") val ownerName: String,
                 @SerializedName("avatar_url") val ownerAvatar: String) {

}