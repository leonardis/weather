package com.leonardis.weather.utils

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async

fun <T> CoroutineScope.lazyCoroutine(
    block: suspend CoroutineScope.() -> T
): Deferred<T> {
    return async(start = CoroutineStart.LAZY) { block() }
}