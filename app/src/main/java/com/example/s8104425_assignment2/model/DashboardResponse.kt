package com.example.s8104425_assignment2.model

//represents the response from the dashboard API
data class DashboardResponse(
    val entities: List<SportEntity>, //list of sports
    val entityTotal: Int // total number of sports
)
