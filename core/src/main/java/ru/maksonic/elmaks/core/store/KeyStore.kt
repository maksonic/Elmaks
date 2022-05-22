package ru.maksonic.elmaks.core.store

import javax.inject.Inject

/**
 * @Author maksonic on 22.05.2022
 */
interface KeyStore {
    val passedCityIdKey: String

    class PassedDataKey @Inject constructor() : KeyStore {
        override val passedCityIdKey: String
            get() = "cityId"
    }
}