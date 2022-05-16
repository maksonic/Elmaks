package ru.maksonic.elmaks.core.data


import kotlin.Exception

/**
 * @author maksonic on 12.05.2022
 */
data class FakeDataException(override val message: String) : Exception(message)
