package com.example.mvvmonboard.ui.auth

import android.content.Intent
import android.view.View
import androidx.lifecycle.ViewModel
import com.example.mvvmonboard.data.repository.UserRepository
import com.example.mvvmonboard.util.ApiException
import com.example.mvvmonboard.util.Coroutines
import com.example.mvvmonboard.util.NoInternetException


/**
 * Created by Subhankar on August'09 2019
 */
class AuthViewModel(private val repository: UserRepository) : ViewModel() {

    var name: String? = null
    var email: String? = null
    var password: String? = null
    var confirmPassword: String? = null

    var authListener: AuthListener? = null

    fun getLoggedInUser() = repository.getUser()

    fun onLoginButtonClick(view: View) {
        if (email.isNullOrEmpty() || password.isNullOrEmpty()) {
            authListener?.onFailure("Invalid email or password")
            return
        }
        authListener?.onStarted()

        Coroutines.main {
            try {
                val response = repository.userLogin(email!!, password!!)
                response.user?.let {
                    authListener?.onSuccess(it)
                    repository.saveUser(it)
                    return@main
                }
                authListener?.onFailure(response.message)
            } catch (e: ApiException) {
                authListener?.onFailure(e.message)
            } catch (e: NoInternetException) {
                authListener?.onFailure(e.message)
            }
        }
    }

    fun onSignup(view: View) {
        Intent(view.context, SignupActivity::class.java).also {
            view.context.startActivity(it)
        }
    }

    fun onSignin(view: View) {
        Intent(view.context, LoginActivity::class.java).also {
            it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            view.context.startActivity(it)
        }
    }

    fun onSignupButtonClick(view: View) {
        if (name.isNullOrEmpty()) {
            authListener?.onFailure("name is required")
            return
        }
        if (email.isNullOrEmpty()) {
            authListener?.onFailure("email is required")
            return
        }
        if (password.isNullOrEmpty()) {
            authListener?.onFailure("Please enter a password")
            return
        }
        if (confirmPassword.isNullOrEmpty()) {
            authListener?.onFailure("Confirm password cant be left blank")
            return
        }
        if (password != confirmPassword) {
            authListener?.onFailure("Password and Confirm password did not match")
            return
        }
        authListener?.onStarted()

        Coroutines.main {
            try {
                val response = repository.userSignup(name!!, email!!, password!!)
                response.user?.let {
                    authListener?.onSuccess(it)
                    repository.saveUser(it)
                    return@main
                }
                authListener?.onFailure(response.message)
            } catch (e: ApiException) {
                authListener?.onFailure(e.message)
            } catch (e: NoInternetException) {
                authListener?.onFailure(e.message)
            }
        }
    }

}