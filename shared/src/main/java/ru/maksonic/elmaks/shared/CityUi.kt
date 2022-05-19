package ru.maksonic.elmaks.shared

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * @author maksonic on 06.05.2022
 */
@Parcelize
data class CityUi(
    val kladrId: Long,
    val name: String,
    val postalCode: Long,
    val population: Long,
    val foundationYear: Int,
    val region: String,
    val regionType: String,
    val federalDistrict: String,
    val timezone: String
): Parcelable