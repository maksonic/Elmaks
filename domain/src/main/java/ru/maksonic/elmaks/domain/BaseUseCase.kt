package ru.maksonic.elmaks.domain

/**
 * @author maksonic on 09.05.2022
 */
interface BaseUseCase<T> {
    operator fun invoke(vararg args: Any? = arrayOf()): T
}