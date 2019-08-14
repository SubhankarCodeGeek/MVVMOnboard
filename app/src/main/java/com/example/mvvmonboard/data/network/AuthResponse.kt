package com.example.mvvmonboard.data.network

import com.example.mvvmonboard.data.db.entities.User

/**
 * Created by Subhankar on August'09 2019
 */

data class AuthResponse(
    val isSuccessful: Boolean? = null,
    val message: String? = null,
    val user: User? = null
)