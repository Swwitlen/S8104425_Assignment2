package com.example.s8104425_assignment2.repository

import com.example.s8104425_assignment2.model.AuthRequest
import com.example.s8104425_assignment2.model.AuthResponse
import com.example.s8104425_assignment2.model.DashboardResponse
import com.example.s8104425_assignment2.network.ApiService
import javax.inject.Inject
import javax.inject.Singleton
import retrofit2.Response

@Singleton
//repository abstracts api call
//provides clean interface for ViewModels to request data
class MainRepository @Inject constructor(private val apiService: ApiService) {

    //Handles login Api call
    suspend fun login(location: String, username: String, password: String): Response<AuthResponse> {
        return apiService.login(location, AuthRequest(username, password))
    }

    //handles dashboard api call
    suspend fun getDashboard(keypass: String): Response<DashboardResponse> {
        return apiService.getDashboard(keypass)
    }
}