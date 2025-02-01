package com.haris.core.domain.model

data class FilterOptions(
    val gender: GenderFilter? = null,
    val height: HeightFilter? = null,
    val searchQuery: String? = null
)

enum class GenderFilter {
    MALE,
    FEMALE
}

enum class HeightFilter {
    DIBAWAH_160,
    ANTARA_160_180,
    DIATAS_180
}