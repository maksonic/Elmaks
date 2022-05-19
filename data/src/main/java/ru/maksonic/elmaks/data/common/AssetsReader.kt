package ru.maksonic.elmaks.data.common

import android.content.Context
import javax.inject.Inject

/**
 * @Author maksonic on 16.05.2022
 */
interface AssetsReader {
    fun readAssetsString(fileName: String): String

    class Reader @Inject constructor(private val context: Context) : AssetsReader {
        override fun readAssetsString(fileName: String): String = context.assets
            .open(fileName)
            .bufferedReader()
            .use { bufferReader -> bufferReader.readText() }

    }
}
