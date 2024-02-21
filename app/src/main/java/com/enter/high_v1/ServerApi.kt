package com.enter.high_v1

import com.enter.high_v1.mypage.MyPageData
import com.enter.high_v1.start.LoginRequest
import com.enter.high_v1.start.LoginResponse
import com.enter.high_v1.start.SignupRequest
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface ServerApi {
    @POST("/user/signup")
    fun signUp(@Body request: SignupRequest): Call<Void>

    @POST("/user/login")
    fun login(@Body request: LoginRequest): Call<LoginResponse>

    @GET("/user/info")
    fun getUserInfo(@Header("Authorization") token : String): Call<MyPageData>

    @POST("/user/logout")
    fun logout(@Header("Authorization") token: String): Call<Void>

    @GET("/feeds")
    fun recommendSchool(
        @Header("Authorization") token: String,
        @Body request: RecommendSchoolRequest
    ): Call<RecommendSchoolResponse>

    @GET("/feeds/info")
    fun schoolInfo(
        @Header("Authorization") token: String,
        @Body request: SchoolInfoRequest
    ): Call<SchoolInfoResponse>
}