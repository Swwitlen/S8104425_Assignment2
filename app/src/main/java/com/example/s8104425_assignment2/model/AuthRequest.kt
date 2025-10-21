package com.example.s8104425_assignment2.model

//represents the JSON body for login API request
data class AuthRequest(
    val username: String,
    val password: String
)