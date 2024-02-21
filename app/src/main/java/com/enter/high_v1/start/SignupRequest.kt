package com.enter.high_v1.start

data class SignupRequest(
    val accountId: String,
    val nickname: String,
    val password: String,
    val email: String
)
