package com.hnam.cache.mapper

/**
 * Created by nampham on 12/17/18.
 */
interface CacheMapper<C, E> {

    fun mapFromCached(cache: C) : E

    fun mapToCached(entity: E): C
}