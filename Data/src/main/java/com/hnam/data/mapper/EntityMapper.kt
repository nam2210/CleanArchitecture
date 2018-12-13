package com.hnam.data.mapper

import javax.swing.text.html.parser.Entity

/**
 * Created by nampham on 12/12/18.
 */
interface EntityMapper<E, D> {

    fun mapFromEntity(entity: E) : D

    fun mapToEntity(domain: D) : E
}