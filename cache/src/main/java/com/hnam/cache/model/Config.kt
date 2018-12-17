package com.hnam.cache.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.hnam.cache.db.ConfigConstants

/**
 * Created by nampham on 12/17/18.
 */
@Entity(tableName = ConfigConstants.TABLE_NAME)
data class Config( @PrimaryKey(autoGenerate = true)
              var id: Int = -1,
              var lastCacheTime: Long) {
}