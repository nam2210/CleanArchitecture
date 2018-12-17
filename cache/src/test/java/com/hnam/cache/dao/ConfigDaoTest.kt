package com.hnam.cache.dao

import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.arch.persistence.room.Room
import com.hnam.cache.db.ProjectsDatabase
import com.hnam.cache.test.factory.ConfigDataFactory
import com.hnam.cache.test.factory.ProjectDataFactory
import org.junit.After
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment

/**
 * Created by nampham on 12/17/18.
 */
@RunWith(RobolectricTestRunner::class)
class ConfigDaoTest {

    @Rule
    @JvmField var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val database = Room.inMemoryDatabaseBuilder(
        RuntimeEnvironment.application.applicationContext,
        ProjectsDatabase::class.java)
        .allowMainThreadQueries()
        .build()

    @After
    fun clearDb() {
        database.close()
    }

    @Test
    fun saveConfigurationSavesData(){
        val config = ConfigDataFactory.makeCachedConfig()
        database.configDao().insertConfig(config)

        val observable = database.configDao().getConfig().test()
        observable.assertValue(config)
    }
}