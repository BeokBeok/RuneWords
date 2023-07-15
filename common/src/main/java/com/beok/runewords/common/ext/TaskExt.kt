package com.beok.runewords.common.ext

import com.google.android.gms.tasks.Task
import kotlin.coroutines.resumeWithException
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.suspendCancellableCoroutine

suspend fun <T> Task<T>.await(): T {
    if (isComplete) {
        val e = exception
        return if (e == null) {
            if (isCanceled) {
                throw CancellationException("Task $this was cancelled normally.")
            } else {
                result as T
            }
        } else {
            throw e
        }
    }

    return suspendCancellableCoroutine { continuation ->
        addOnCompleteListener {
            val e = exception
            if (e == null) {
                if (isCanceled) continuation.cancel() else continuation.resume(result as T, null)
            } else {
                continuation.resumeWithException(e)
            }
        }
    }
}
