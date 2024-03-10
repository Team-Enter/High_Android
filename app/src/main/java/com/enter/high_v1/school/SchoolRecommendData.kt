package com.enter.high_v1.school

data class SchoolRecommendData(
    val name: String,
    val Stype: String,
    val location: String
)

data class SchoolRecommendResponse(
    val firstData: List<SchoolRecommendData>,
    val secondData: List<SchoolRecommendData>
)
