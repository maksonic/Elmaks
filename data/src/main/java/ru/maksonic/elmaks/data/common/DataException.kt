package ru.maksonic.elmaks.data.common

import kotlin.Exception

/**
 * @author maksonic on 12.05.2022
 */
data class FakeDataException(override val message: String) : Exception(message)
data class EmptyCacheException(override val message: String) : Exception(message)
data class CachedItemNotFound(override val message: String) : Exception(message)
