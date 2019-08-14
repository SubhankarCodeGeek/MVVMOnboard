package com.example.mvvmonboard.data.network

import com.example.mvvmonboard.util.ApiException
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Response


/**
 * Created by Subhankar on August'09 2019
 */

abstract class SafeApiRequest {

    suspend fun <T : Any> apiRequest(call: suspend () -> Response<T>): T {
        val response = call.invoke()

        if (response.isSuccessful) {
            return response.body()!!
        } else {
            val error = response.errorBody()?.string()
            val message = StringBuilder()

            error?.let {
                try {
                    message.append(JSONObject(it).get("message"))
                } catch (e: JSONException) {
                }

                message.append("\n")
            }
            message.append("ERROR CODE: ${response.code()}")
            throw ApiException(message.toString())
        }
    }
}