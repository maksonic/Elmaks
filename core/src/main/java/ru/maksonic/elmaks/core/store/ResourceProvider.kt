package ru.maksonic.elmaks.core.store

import android.content.Context
import androidx.annotation.StringRes
import javax.inject.Inject

/**
 * @author maksonic on 01.05.2022
 */
interface ResourceProvider {
    fun getString(@StringRes id: Int, vararg formatArgs: Any?): String

    class Base @Inject constructor(private val context: Context) : ResourceProvider {

        override fun getString(id: Int, vararg formatArgs: Any?) =
            context.getString(id, *formatArgs)
    }
}