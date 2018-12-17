package com.hnam.cache.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.hnam.cache.dao.CachedProjectsDao
import com.hnam.cache.dao.ConfigDao
import com.hnam.cache.model.CachedProject
import com.hnam.cache.model.Config
import javax.inject.Inject

/**
 * Created by nampham on 12/17/18.
 */
@Database(entities = arrayOf(
    CachedProject::class, Config::class), version = 2)
abstract class ProjectsDatabase @Inject constructor(): RoomDatabase() {

    abstract fun cachedProjectsDao(): CachedProjectsDao

    abstract fun configDao(): ConfigDao

    companion object {
        private var INSTANCE: ProjectsDatabase? = null
        private val lock = Any()

        fun getInstance(context: Context): ProjectsDatabase {
            if (INSTANCE == null) {
                synchronized(lock) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(context.applicationContext,
                            ProjectsDatabase::class.java, "projects.db")
                            .build()
                    }
                    return INSTANCE as ProjectsDatabase
                }
            }
            return INSTANCE as ProjectsDatabase
        }
    }

}