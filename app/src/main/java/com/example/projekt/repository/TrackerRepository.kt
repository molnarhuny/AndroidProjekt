package com.example.projekt.repository

import retrofit2.Response
import com.example.projekt.api.RetrofitInstance
import com.example.projekt.model.LoginRequest
import com.example.projekt.model.LoginResponse
import com.example.projekt.model.User

class TrackerRepository {
    suspend fun login(request: LoginRequest): Response<LoginResponse> {
        return RetrofitInstance.api.login(request)
    }

    suspend fun getUsers(token: String): Response<List<User>> {
        return RetrofitInstance.api.getUsers(token)
    }
}