package com.enter.high_v1.school


data class SchoolInfoResponse(
    val name: String,
    val Stype: String,
    val location: String,
    val phone: String,
    val date: String,
    val Etype: String,
    val gender: String,
    val link: String,
    val lesson: List<String>
)
