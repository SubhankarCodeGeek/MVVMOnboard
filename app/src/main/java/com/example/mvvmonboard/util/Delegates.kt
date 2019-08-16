package com.example.mvvmonboard.util

import kotlinx.coroutines.*


/**
 * Created by Subhankar on August'16 2019
 */

fun <T> lazyDeferred(block: suspend CoroutineScope.() -> T): Lazy<Deferred<T>> {
    return lazy {
        GlobalScope.async(start = CoroutineStart.LAZY) {
            block.invoke(this)
        }
    }
}