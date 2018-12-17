package com.hnam.presentation.mapper

/**
 * Created by nampham on 12/17/18.
 */
interface Mapper<out V, in D> {

    fun mapToView(type: D): V

}