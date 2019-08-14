package com.example.mvvmonboard.ui.auth

import com.example.mvvmonboard.data.db.entities.User

/**
 * Created by Subhankar on August'09 2019
 */

interface AuthListener {

    fun onStarted()

    fun onSuccess(user: User?)

    fun onFailure(message: String?)
}