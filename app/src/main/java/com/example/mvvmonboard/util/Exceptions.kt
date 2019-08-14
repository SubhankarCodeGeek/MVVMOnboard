package com.example.mvvmonboard.util

import java.io.IOException


/**
 * Created by Subhankar on August'09 2019
 */

class ApiException(message: String) : IOException(message)
class NoInternetException(message: String):IOException(message)