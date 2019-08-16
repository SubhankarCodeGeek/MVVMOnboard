package com.example.mvvmonboard.data.repository

import com.example.mvvmonboard.data.db.AppDatabase
import com.example.mvvmonboard.data.db.entities.User
import com.example.mvvmonboard.data.network.response.AuthResponse
import com.example.mvvmonboard.data.network.MyApi
import com.example.mvvmonboard.data.network.SafeApiRequest

/**
 * Created by Subhankar on August'09 2019
 */

class UserRepository(
    private val api: MyApi,
    private val database: AppDatabase
) : SafeApiRequest() {

    suspend fun userLogin(email: String, password: String): AuthResponse {
        return apiRequest { api.userLogin(email, password) }
    }

    suspend fun userSignup(name: String, email: String, password: String): AuthResponse {
        return apiRequest { api.userSignup(name, email, password) }
    }

    suspend fun saveUser(user: User) = database.getUserDao().upsert(user)

    fun getUser() = database.getUserDao().getUser()
}