package com.enter.high_v1.start

data class LoginRequest(
    val accountId: String,
    val password: String
)

data class LoginResponse(
    val accessToken: String
)