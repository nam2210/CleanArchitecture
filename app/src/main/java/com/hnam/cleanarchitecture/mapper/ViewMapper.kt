package com.hnam.cleanarchitecture.mapper

/**
 * Created by nampham on 12/21/18.
 */
interface ViewMapper<in P, out V> {

    fun mapToView(presentation: P): V

}