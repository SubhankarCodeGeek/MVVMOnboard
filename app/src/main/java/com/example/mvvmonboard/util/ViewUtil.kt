package com.example.mvvmonboard.util

import android.content.Context
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar

/**
 * Created by Subhankar on August'09 2019
 */

fun Context?.toast(message: String?) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun ProgressBar?.show() {
    this?.visibility = View.VISIBLE
}

fun ProgressBar?.hide() {
    this?.visibility = View.GONE
}

fun View.snackbar(message: String?) {
    message?.let { Snackbar.make(this, it, Snackbar.LENGTH_SHORT) }.also { snackbar ->
        snackbar?.setAction("Ok") {
            snackbar.dismiss()
        }?.show()
    }
}