package com.enter.high_v1

import android.util.Log

class Token {
    private var token: String = ""

    fun getToken(): String {
        Log.d("token", "getToken : $token")
        return token
    }
    fun setToken(token: String) {
        this.token = token
        Log.d("token", "setToken : $token")
    }
}