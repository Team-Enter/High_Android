package com.enter.high_v1

import com.enter.high_v1.inspection.InspectionQuestion
import com.enter.high_v1.inspection.InspectionResult
import com.enter.high_v1.inspection.InspectionResultRequest
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface OpenServerApi {
    @GET("inspct/openapi/test/questions?apikey=0586793f04ef67f0aadabc55dc7637a6&q=20")
    fun getInspectionQuestion() : Call<InspectionQuestion>

    @POST("/report")
    fun getInspectionResult(@Body request: InspectionResultRequest) : Call<InspectionResult>
}