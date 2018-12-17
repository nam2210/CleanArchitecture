package com.hnam.cache.test.factory

import com.hnam.cache.model.Config


/**
 * Created by nampham on 12/17/18.
 */
object ConfigDataFactory {

    fun makeCachedConfig(): Config {
        return Config(DataFactory.randomInt(), DataFactory.randomLong())
    }

}