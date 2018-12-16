package com.hnam.remote.mapper

/**
 * Created by nampham on 12/16/18.
 */
interface ModelMapper<in M, out E> {
    fun mapFromModel(model: M): E
}