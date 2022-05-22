package ru.maksonic.elmaks.domain

/**
 * @author maksonic on 09.05.2022
 */
interface BaseUseCase<T, D> {
    operator fun invoke(args: D? = null): T
}