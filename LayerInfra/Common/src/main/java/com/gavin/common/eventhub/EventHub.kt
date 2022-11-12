package com.gavin.common.eventhub

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import java.util.concurrent.ConcurrentHashMap
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

object EventHub {

    private val mutex by lazy { Mutex() }
    private val eventFlows = ConcurrentHashMap<String, MutableSharedFlow<Any>>()
    private val stickyEventFlows = ConcurrentHashMap<String, MutableSharedFlow<Any>>()

    suspend fun eventFlow(eventName: String, isSticky: Boolean): MutableSharedFlow<Any> {
        return if (isSticky) {
            stickyEventFlows[eventName]
        } else {
            eventFlows[eventName]
        } ?: mutex.withLock {
            if (isSticky) {
                stickyEventFlows[eventName]
            } else {
                eventFlows[eventName]
            } ?: MutableSharedFlow<Any>(
                replay = if (isSticky) 1 else 0,
                extraBufferCapacity = Int.MAX_VALUE
            ).also {
                if (isSticky) {
                    stickyEventFlows[eventName] = it
                } else {
                    eventFlows[eventName] = it
                }
            }
        }
    }

    suspend inline fun <reified T> observeEvent(
        isSticky: Boolean = false,
        crossinline block: suspend (T) -> Unit
    ) {
        eventFlow(T::class.java.name, isSticky).collectLatest {
            if (it is T) {
                kotlin.runCatching {
                    block(it)
                }
            }
        }
    }

    suspend inline fun <reified T : Any> postEvent(
        event: T,
        isSticky: Boolean = false,
        delayMillis: Long? = null
    ) {
        delayMillis?.also {
            delay(it)
        }
        event::class.java.name.also { eventName ->
            setOf(false, isSticky).map {
                eventFlow(eventName, it)
            }.forEach { flow ->
                flow.emit(event)
            }
        }
    }
}

inline fun <reified T> CoroutineScope.observeEvent(
    isSticky: Boolean = false,
    context: CoroutineContext = EmptyCoroutineContext,
    start: CoroutineStart = CoroutineStart.DEFAULT,
    crossinline block: suspend (T) -> Unit
): Job {
    return launch(context = context, start = start) {
        EventHub.observeEvent(isSticky, block)
    }
}