package com.beok.runewords.common.ext

import com.google.android.gms.tasks.CancellationTokenSource
import com.google.android.gms.tasks.RuntimeExecutionException
import com.google.android.gms.tasks.Task
import com.google.android.gms.tasks.TaskCompletionSource
import kotlin.coroutines.resumeWithException
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.suspendCancellableCoroutine

fun <T> Deferred<T>.asTask(): Task<T> {
    val cancellation = CancellationTokenSource()
    val source = TaskCompletionSource<T>(cancellation.token)

    invokeOnCompletion {
        if (it is CancellationException) {
            cancellation.cancel()
            return@invokeOnCompletion
        }

        @Suppress("EXPERIMENTAL_API_USAGE")
        val throwable = getCompletionExceptionOrNull()
        if (throwable == null) {
            @Suppress("EXPERIMENTAL_API_USAGE")
            source.setResult(getCompleted())
        } else {
            source.setException(throwable as? Exception ?: RuntimeExecutionException(throwable))
        }
    }

    return source.task
}

fun <T> Task<T>.asDeferredAsync(): Deferred<T> {
    if (isComplete) {
        val e = exception
        return if (e == null) {
            CompletableDeferred<T>().apply {
                @Suppress("UNCHECKED_CAST")
                if (isCancelled) cancel() else complete(result as T)
            }
        } else {
            CompletableDeferred<T>().apply { completeExceptionally(e) }
        }
    }

    val result = CompletableDeferred<T>()
    addOnCompleteListener {
        val e = it.exception
        if (e == null) {
            @Suppress("UNCHECKED_CAST")
            if (isCanceled) result.cancel() else result.complete(it.result as T)
        } else {
            result.completeExceptionally(e)
        }
    }

    return result
}

suspend fun <T> Task<T>.await(): T {
    if (isComplete) {
        val e = exception
        return if (e == null) {
            if (isCanceled) {
                throw CancellationException("Task $this was cancelled normally.")
            } else {
                @Suppress("UNCHECKED_CAST")
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
                @Suppress("UNCHECKED_CAST")
                if (isCanceled) continuation.cancel() else continuation.resume(result as T, null)
            } else {
                continuation.resumeWithException(e)
            }
        }
    }
}
