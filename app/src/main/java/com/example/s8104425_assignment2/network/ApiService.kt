package com.example.s8104425_assignment2.network

import com.example.s8104425_assignment2.model.AuthRequest
import com.example.s8104425_assignment2.model.AuthResponse
import com.example.s8104425_assignment2.model.DashboardResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {

    //Login Api endpoint
    @POST("{location}/auth")
    //suspend allows coroutine- based asynchronous calls
    suspend fun login(
        @Path("location") location: String, //location is footscray
        @Body body: AuthRequest //send username and password
    ): Response<AuthResponse> //return HTTP response with AuthResponse body

    //Dashboard Api endpoint
    @GET("dashboard/{keypass}")
    suspend fun getDashboard(
        @Path("keypass") keypass: String //key returned from login
    ): Response<DashboardResponse>
}