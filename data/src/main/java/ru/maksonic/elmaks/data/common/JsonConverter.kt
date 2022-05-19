package ru.maksonic.elmaks.data.common

import java.io.IOException
import javax.inject.Inject

/**
 * @author maksonic on 06.05.2022
 */
interface JsonConverter {
    fun convertAssertJsonToString(fileName: String): Result<String>

    class Converter @Inject constructor(private val assetsReader: AssetsReader) : JsonConverter {

        override fun convertAssertJsonToString(fileName: String): Result<String> {
            return try {
                val readString = assetsReader.readAssetsString(fileName)
                Result.success(readString)
            } catch (e: IOException) {
                Result.failure(e)
            }
        }
    }
}
