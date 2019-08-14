package com.example.mvvmonboard.data.network

import android.content.Context
import android.net.ConnectivityManager
import com.example.mvvmonboard.util.NoInternetException
import okhttp3.Interceptor
import okhttp3.Response


/**
 * Created by Subhankar on August'10 2019
 */

class NetworkConnectionInterceptor(context: Context) : Interceptor {

    private val applicationContext = context.applicationContext

    override fun intercept(chain: Interceptor.Chain): Response {
        if (!isInternetAvailable()) {
            throw NoInternetException("Make sure you have an active data connection")
        }
        return chain.proceed(chain.request())
    }


    private fun isInternetAvailable(): Boolean {
        val connectivityManager =
            applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        connectivityManager.activeNetworkInfo.also {
            return it != null && it.isConnected
        }
    }
}