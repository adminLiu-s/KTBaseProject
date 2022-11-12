package com.gavin.common

import com.gavin.common.coroutine.ApplicationCoroutineScope
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope

@EntryPoint
@InstallIn(SingletonComponent::class)
interface ApplicationEntryPoint {

    @ApplicationCoroutineScope
    fun applicationScope(): CoroutineScope

}