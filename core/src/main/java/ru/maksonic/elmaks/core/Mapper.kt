package ru.maksonic.elmaks.core

/**
 * @author maksonic on 06.05.2022
 */
interface Mapper<I, O> {

    fun mapTo(i: I): O

    fun mapFrom(o: O): I

    fun mapFromList(list: List<O>): List<I> {
        return list.mapNotNull { mapFrom(it) }
    }

    fun mapToList(list: List<I>): List<O> {
        return list.mapNotNull { mapTo(it) }
    }
}